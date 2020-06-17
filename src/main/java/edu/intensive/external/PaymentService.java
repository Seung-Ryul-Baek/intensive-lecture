package edu.intensive.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="payment", url="${feign.payment.url}")
public interface PaymentService {
    @RequestMapping(method = RequestMethod.POST, path="/payments")
    public void enroll(@RequestBody Payment payment);
}
