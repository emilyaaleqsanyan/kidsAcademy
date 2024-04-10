package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.CourseSchedule;
import am.itspace.kidsacademy.repository.CourseScheduleRepository;
import am.itspace.kidsacademy.service.CourseScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseScheduleServiceImpl implements CourseScheduleService {

    private final CourseScheduleRepository courseScheduleRepository;

    @Override
    public void findAll(ModelMap modelMap) {
        List<CourseSchedule> scheduleList = courseScheduleRepository.findAll();
        log.info("Found " + scheduleList.size() + " schedules");
        modelMap.addAttribute("courseSchedule", scheduleList);
    }

    @Override
    public CourseSchedule save(CourseSchedule courseSchedule) {
        CourseSchedule save = courseScheduleRepository.save(courseSchedule);
        log.info("Saved courseSchedule by id " + save.getId());
        return save;
    }

    @Override
    public void delete(int id) {
        Optional<CourseSchedule> byId = courseScheduleRepository.findById(id);
        if (byId.isPresent()) {
            CourseSchedule courseSchedule = byId.get();
            courseScheduleRepository.deleteById(courseSchedule.getId());
            log.info("Deleted courseSchedule by id " + courseSchedule.getId());
        }
    }


    @Override
    public CourseSchedule findById(int id) {
        Optional<CourseSchedule> byId = courseScheduleRepository.findById(id);
        if(byId.isPresent()){
            log.info("Found courseSchedule by id " + byId.get().getId());
            return byId.get();
        }
        log.info("Course Schedule not found by id " + id);
        return null;
    }


    @Override
    public CourseSchedule update(CourseSchedule courseSchedule) {
        CourseSchedule save = courseScheduleRepository.save(courseSchedule);
        log.info("Updated courseSchedule by id " + save.getId());
        return save;
    }
}
