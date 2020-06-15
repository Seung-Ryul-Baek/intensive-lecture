package edu.intensive.external;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Payment {

    Long paymentId;
    Long studentId;
    Long courseId;
    String status;
}
