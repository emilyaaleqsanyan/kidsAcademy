package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;

public interface CourseService {

    Course save(Course course);

    Page<Course> findAll(Pageable pageable);

    Page<Course> findAll(Pageable pageable, ModelMap modelMap, int id);

    void findAllAndAddToModelMap(ModelMap modelMap);

    Course findById(int id);


    Course update(Course course);

    void delete(int id);

    void coursePage(Pageable page,ModelMap modelMap);
}


