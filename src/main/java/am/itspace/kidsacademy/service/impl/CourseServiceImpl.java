package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.Course;
import am.itspace.kidsacademy.entity.User;
import am.itspace.kidsacademy.repository.CourseRepository;
import am.itspace.kidsacademy.repository.UserRepository;
import am.itspace.kidsacademy.service.CourseService;
import am.itspace.kidsacademy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id).orElse(null);
    }
}




