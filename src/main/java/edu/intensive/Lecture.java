package edu.intensive;

import edu.intensive.external.CourseService;
import edu.intensive.external.Payment;
import edu.intensive.external.PaymentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Lecture {
    @Id
    @GeneratedValue
    Long lectureId;
    Long studentId;
    Long courseId;
    String status;

    @PrePersist
    public void onPrePersist() {


        LectureRequested lectureRequested = new LectureRequested();
        BeanUtils.copyProperties(this, lectureRequested);
        lectureRequested.publishAfterCommit();
    }
    @PostPersist
    public void onPostPersist() {
        Payment payment = new Payment();
        payment.setCourseId(this.courseId);
        payment.setStatus("Payment Requested");
        payment.setStudentId(this.studentId);
//        String response = LectureApplication.applicationContext.getBean(CourseService.class).selectAll();
        ResponseEntity courseResponse = LectureApplication.applicationContext.getBean(CourseService.class).selectOne(this.courseId);
        ResponseEntity courseResponse = LectureApplication.applicationContext.getBean(CourseService.class).selectOne(this.courseId);


        (response.getStatusCode().equals(HttpStatus.OK)
        LectureApplication.applicationContext.getBean(PaymentService.class).enroll(payment);
    }

    @PreUpdate
    public void onPreUpdate() {
        if(this.getStatus().equals("completed")) {
            LectureCompleted lectureCompleted = new LectureCompleted();
            BeanUtils.copyProperties(this, lectureCompleted);
            lectureCompleted.publishAfterCommit();
        }
        else if (this.getStatus().equals("approved")) {
            LectureApproved lectureApproved = new LectureApproved();
            BeanUtils.copyProperties(this, lectureApproved);
            lectureApproved.publishAfterCommit();
        }
        else if (this.getStatus().equals("canceled")) {
            LectureCanceled lectureCanceled = new LectureCanceled();
            BeanUtils.copyProperties(this, lectureCanceled);
            lectureCanceled.publishAfterCommit();
        }
    }
    @PreRemove
    public void onPreRemove() {
    }
    @PostRemove
    public void onPostRemove() {
    }
}
