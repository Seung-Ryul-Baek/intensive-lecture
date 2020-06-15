package edu.intensive.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="course", url="localhost:8081")
public interface CourseService {
    @RequestMapping(method = RequestMethod.GET, path="/courses")
    public String selectAll();
    @RequestMapping(method = RequestMethod.GET, path="/courses/{courseId}")
    public ResponseEntity selectOne(@PathVariable Long courseId);
}
