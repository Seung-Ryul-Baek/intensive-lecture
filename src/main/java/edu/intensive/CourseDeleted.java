package edu.intensive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CourseDeleted extends AbstractEvent {
    Long courseId;
    String courseName;

    public CourseDeleted() {
        super();
    }
}
