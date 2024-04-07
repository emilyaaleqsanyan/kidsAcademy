package am.itspace.kidsacademy.controller;

import am.itspace.kidsacademy.service.CourseScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final CourseScheduleService courseScheduleService;


    @GetMapping("/schedulePage")
    public String coursePage(ModelMap modelMap) {
        courseScheduleService.findAll(modelMap);
        return "/user/schedulePage";
    }
}
