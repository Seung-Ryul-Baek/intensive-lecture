package edu.intensive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LectureEvaluated extends AbstractEvent{
    Long lectureId;
    Long studentId;
    Long courseId;
    String status;
    Boolean paid;
    Boolean completed;
    Boolean canceled;
    Double grade;
    public LectureEvaluated() {
        super();
    }
}
