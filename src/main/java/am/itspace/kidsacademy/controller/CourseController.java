package am.itspace.kidsacademy.controller;

import am.itspace.kidsacademy.entity.Course;
import am.itspace.kidsacademy.service.CourseScheduleService;
import am.itspace.kidsacademy.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseScheduleService courseScheduleService;


    @GetMapping("/coursePage")
    public String coursePage(@PageableDefault(size = 4, page = 1) Pageable page,
                             ModelMap modelMap) {
        courseService.coursePage(page, modelMap);
        return "user/coursePage";
    }


    @GetMapping("/courses/{id}")
    public String courseSinglePage(@PathVariable("id") int id,
                                   ModelMap modelMap) {
        Course byId = courseService.findById(id);
        courseScheduleService.findAll(modelMap);
        if (byId == null) {
            return "redirect:/";
        }
        modelMap.addAttribute("course", byId);
        return "user/singleCourse";
    }
}
