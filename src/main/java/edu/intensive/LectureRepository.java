package edu.intensive;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface LectureRepository extends PagingAndSortingRepository<Lecture, Long> {

    Lecture[] findByCourseId(Long courseId);
    Lecture[] findByCourseIdAndStudentId(Long courseId, Long studentId);
}
