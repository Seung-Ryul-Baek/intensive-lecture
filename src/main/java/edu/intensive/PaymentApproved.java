package edu.intensive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentApproved extends AbstractEvent {
    Long paymentId;
    Long courseId;
    Long studentId;
    String status;

    public PaymentApproved() {
        super();
    }
}
