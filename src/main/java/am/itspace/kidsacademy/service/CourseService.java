package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;


public interface CourseService {

    Course save(Course course);

    Page<Course> findAll(Pageable pageable, ModelMap modelMap, int id);

    Course findById(int id);


}


