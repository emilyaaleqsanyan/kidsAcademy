package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.CourseSchedule;
import am.itspace.kidsacademy.repository.CourseScheduleRepository;
import am.itspace.kidsacademy.service.CourseScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseScheduleServiceImpl implements CourseScheduleService {

    private final CourseScheduleRepository courseScheduleRepository;

    @Override
    public void findAll(ModelMap modelMap) {
        List<CourseSchedule> scheduleList = courseScheduleRepository.findAll();
        modelMap.addAttribute("courseSchedule", scheduleList);
    }

    @Override
    public CourseSchedule save(CourseSchedule courseSchedule) {
        return courseScheduleRepository.save(courseSchedule);
    }

    @Override
    public void delete(int id) {
        Optional<CourseSchedule> byId = courseScheduleRepository.findById(id);
        if (byId.isPresent()) {
            CourseSchedule courseSchedule = byId.get();
            courseScheduleRepository.deleteById(courseSchedule.getId());
        }
    }


    @Override
    public CourseSchedule findById(int id) {
        return courseScheduleRepository.findById(id).orElse(null);
    }


    @Override
    public CourseSchedule update(CourseSchedule courseSchedule) {
        return courseScheduleRepository.save(courseSchedule);
    }
}
