package edu.intensive.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="student", url="${feign.student.url}")
public interface StudentService {
    @RequestMapping(method = RequestMethod.GET, path="/students")
    public String selectAll();
    @RequestMapping(method = RequestMethod.GET, path="/students/{studentId}")
    public ResponseEntity selectOne(@PathVariable Long studentId);
}
