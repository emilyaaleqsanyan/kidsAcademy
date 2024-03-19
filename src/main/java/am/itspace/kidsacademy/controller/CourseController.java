package am.itspace.kidsacademy.controller;

import am.itspace.kidsacademy.entity.Course;
import am.itspace.kidsacademy.security.SpringUser;
import am.itspace.kidsacademy.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/coursePage")
    public String coursePage(
            @PageableDefault(size = 4, page = 1) Pageable page,
            @AuthenticationPrincipal SpringUser springUser, ModelMap modelMap) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getPageSize());
        Page<Course> coursesPage = courseService.findAll(pageable);
        modelMap.addAttribute("courses", coursesPage);

        int totalPages = coursesPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "user/coursePage";
    }


    @GetMapping("/courses/{id}")
    public String courseSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Course byId = courseService.findById(id);
        if (byId == null) {
            return "redirect:/";
        }
        modelMap.addAttribute("course", byId);


        return "user/singleCourse";

    }

}
