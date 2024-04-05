package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.Course;
import am.itspace.kidsacademy.repository.CourseRepository;
import am.itspace.kidsacademy.service.CourseService;
import am.itspace.kidsacademy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;

    @Autowired
    public CourseServiceImpl(
            CourseRepository courseRepository,
            @Lazy TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Page<Course> findAll(Pageable pageable,ModelMap modelMap, int id) {
        if(id != -1){
            teacherService.findById(id,modelMap);
        }
        modelMap.addAttribute("courses",courseRepository.findAll(pageable));
        return null;
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id).orElse(null);
    }
}


