package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.CourseSchedule;
import org.springframework.ui.ModelMap;

public interface CourseScheduleService {

    CourseSchedule save(CourseSchedule courseSchedule);

    void findAll(ModelMap modelMap);

    void delete(int id);

    CourseSchedule findById(int id);

    CourseSchedule update(CourseSchedule courseSchedule);
}


