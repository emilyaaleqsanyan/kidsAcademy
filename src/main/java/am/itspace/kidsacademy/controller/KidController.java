package am.itspace.kidsacademy.controller;

import am.itspace.kidsacademy.entity.Kid;
import am.itspace.kidsacademy.security.SpringUser;
import am.itspace.kidsacademy.service.CourseService;
import am.itspace.kidsacademy.service.KidService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class KidController {

    private final KidService kidService;
    private final CourseService courseService;


    @PostMapping("/user/courseRegister")
    public String kidRegister(@ModelAttribute Kid kid,
                              @AuthenticationPrincipal SpringUser springUser,
                              @RequestParam("courseId") int id) {
        return kidService.save(kid, springUser, id);
    }


    @GetMapping("/kidsPage")
    public String kidsPage(@AuthenticationPrincipal SpringUser springUser,
                           ModelMap modelMap) {
        return kidService.kidPage(modelMap, springUser);
    }


    @GetMapping("/kid/delete/{id}")
    public String deleteKid(@PathVariable("id") int id) {
        kidService.delete(id);
        return "redirect:/kidsPage";

    }
}


