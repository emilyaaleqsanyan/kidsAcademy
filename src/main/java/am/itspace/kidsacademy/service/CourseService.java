package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    Course save(Course course);

    Page<Course> findAll(Pageable pageable);

    Course findById(int id);


}


