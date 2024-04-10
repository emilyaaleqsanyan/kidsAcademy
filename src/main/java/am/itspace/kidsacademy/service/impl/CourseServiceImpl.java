package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.Course;
import am.itspace.kidsacademy.entity.CourseSchedule;
import am.itspace.kidsacademy.repository.CourseRepository;
import am.itspace.kidsacademy.repository.CourseScheduleRepository;
import am.itspace.kidsacademy.service.CourseService;
import am.itspace.kidsacademy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final CourseScheduleRepository courseScheduleRepository;



    @Autowired
    public CourseServiceImpl(
            CourseRepository courseRepository,
            CourseScheduleRepository courseScheduleRepository,
            @Lazy TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.courseScheduleRepository = courseScheduleRepository;
    }

    @Override
    public Course save(Course course) {
        Course save = courseRepository.save(course);
        log.info("Saved: " + save.getCourseName());
        return save;
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        Page<Course> all = courseRepository.findAll(pageable);
        log.info("Found " + all.getTotalElements() + ", " + all.getTotalPages());
        return all;
    }

    @Override
    public Page<Course> findAll(Pageable pageable, ModelMap modelMap, int id) {
        if(id != -1){
            teacherService.findById(id, modelMap);
        }
        modelMap.addAttribute("courses", courseRepository.findAll(pageable));
        return null;
    }

    @Override
    public Course findById(int id) {
        Optional<Course> byId = courseRepository.findById(id);
        if(byId.isPresent()){
            log.info("Found " + byId.get().getCourseName());
            return byId.get();
        }
        return null;
    }

    @Override
    public void findAllAndAddToModelMap(ModelMap modelMap) {
        List<Course> allCourse = courseRepository.findAll();
        log.info("Found " + allCourse.size() + " " + "courses");
        modelMap.addAttribute("allCourse", allCourse);
    }


    @Override
    public Course update(Course course) {
        Course save = courseRepository.save(course);
        log.info("Updated: " + save.getCourseName());
        return save;
    }

    @Override
    public void delete(int id) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            Course course = byId.get();
            List<CourseSchedule> courseScheduleList = course.getCourseScheduleList();

            courseScheduleRepository.deleteAll(courseScheduleList);
            log.info("Deleted: " + courseScheduleList.size() + " courseSchedule");
            courseRepository.deleteById(course.getId());
            log.info("Deleted course by id: " + course.getId() + "/ " + course.getCourseName());
        }
    }

    @Override
    public void coursePage(Pageable page, ModelMap modelMap) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getPageSize());
        Page<Course> coursesPage = courseRepository.findAll(pageable);
        modelMap.addAttribute("courses", coursesPage);

        int totalPages = coursesPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
    }
}




