package edu.intensive.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="certification", url="${feign.certification.url}")
public interface CertificationService {
    @RequestMapping(method = RequestMethod.GET, path="/certifications/{studentId}/{courseId}", produces = "application/json;charset=UTF-8")
    public Certification getCertification(@PathVariable (value = "courseId") Long courseId, @PathVariable (value = "studentId") Long studentId);

    @RequestMapping(method = RequestMethod.POST, path="/certifications/")
    public void certi(@RequestBody Certification certification);

}
