package edu.intensive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LectureApproved  extends AbstractEvent{
    Long lectureId;
    Long studentId;
    Long courseId;
    String status;

    public LectureApproved() {
        super();
    }
}
