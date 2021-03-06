package edu.intensive;

import edu.intensive.external.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;

@Entity
@Getter @Setter
@Slf4j
public class Lecture {
    @Id
    @GeneratedValue
    Long lectureId;
    Long studentId;
    Long courseId;
    String status;
    Boolean paid;
    Boolean completed;
    Boolean canceled;
    Double grade;

    @PrePersist
    public void onPrePersist() {
        LectureRequested lectureRequested = new LectureRequested();
        lectureRequested.setStatus("Enrolled");
        lectureRequested.setCompleted(false);
        lectureRequested.setPaid(false);
        lectureRequested.setCanceled(false);

        BeanUtils.copyProperties(this, lectureRequested);

        if(this.getCourseId() == null || this.getStudentId() == null) {
            log.error("Lecture Request Error : Type CourseId and StudentId");
            this.setStatus("CourseId And StudentId are needed");
            return;
        } else {
            lectureRequested.publishAfterCommit();
        }
    }

    @PostPersist
    public void onPostPersist() {
        Payment payment = new Payment();
        payment.setCourseId(this.courseId);
        payment.setStudentId(this.studentId);
        payment.setStatus("Payment Requested");

        ResponseEntity studentResponse = LectureApplication.applicationContext.getBean(StudentService.class).selectOne(this.studentId);
        ResponseEntity courseResponse = LectureApplication.applicationContext.getBean(CourseService.class).selectOne(this.courseId);

        if(courseResponse.getStatusCode().equals(HttpStatus.OK) && studentResponse.getStatusCode().equals(HttpStatus.OK)) {
            LectureApplication.applicationContext.getBean(PaymentService.class).enroll(payment);
        }
        else {
            log.info("Payment Request Error : Check CourseId And StudentId");
        }
    }

    @PreUpdate
    public void onPreUpdate() {
        if(this.getStatus().equals("completed")) {
            this.setCompleted(true);
            Certification certification = new Certification();
            certification.setCourseId(this.courseId);
            certification.setStatus("Certified");
            certification.setStudentId(this.studentId);

            LectureApplication.applicationContext.getBean(CertificationService.class).certi(certification);


        }
        else if (this.getStatus().equals("Paid")) {
            LectureApproved lectureApproved = new LectureApproved();
            BeanUtils.copyProperties(this, lectureApproved);
            lectureApproved.publishAfterCommit();
        }
        else if (this.getStatus().equals("canceled")) {
            LectureCanceled lectureCanceled = new LectureCanceled();
            BeanUtils.copyProperties(this, lectureCanceled);
            lectureCanceled.publishAfterCommit();
        }
        else if (this.getStatus().equals("evaluated")) {
            Certification certification = LectureApplication.applicationContext.getBean(CertificationService.class).getCertification(this.courseId, this.studentId);

            if(certification.getStatus()!=null && certification.getStatus().equals("Certified") && this.getGrade()!=null) {
                LectureEvaluated lectureEvaluated = new LectureEvaluated();
                BeanUtils.copyProperties(this, lectureEvaluated);
                lectureEvaluated.publishAfterCommit();
            }
        }
    }
    @PreRemove
    public void onPreRemove() {
    }
    @PostRemove
    public void onPostRemove() {
    }
}
