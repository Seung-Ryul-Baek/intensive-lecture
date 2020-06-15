package edu.intensive;

import edu.intensive.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHandler {
    @Autowired
    LectureRepository lectureRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onEvent(@Payload String message) {
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCourseDeleted(@Payload CourseDeleted courseDeleted) {
        if (courseDeleted.isMe()) {
            Long deletedCourseId = courseDeleted.getCourseId();
            Lecture[] lectures = lectureRepository.findByCourseId(deletedCourseId);
            for (Lecture lecture: lectures) {
                lectureRepository.delete(lecture);
            }
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCourseDeleted(@Payload PaymentApproved paymentApproved) {
        if (paymentApproved.isMe()) {
            Lecture[] lectures = lectureRepository.findByCourseIdAndStudentId(paymentApproved.getCourseId(), paymentApproved.getStudentId());
            for (Lecture lecture: lectures) {
                lecture.setStatus("Paid");
                lectureRepository.save(lecture);
            }
        }
    }
}
