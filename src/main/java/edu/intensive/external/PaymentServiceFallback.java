package edu.intensive.external;

import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallback implements PaymentService {

	@Override
	public void enroll(Payment payment) {
		System.out.println("Circuit breaker has been opened. Fallback returned instead.");
	}

}
