package edu.intensive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LectureRequested extends AbstractEvent {
    Long lectureId;
    Long studentId;
    Long courseId;
    String status;
    public LectureRequested() {
        super();
    }
}
