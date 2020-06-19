package edu.intensive.external;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
public class Certification {
    Long certificationId;
    Long studentId;
    Long courseId;
    String status;
}
