package am.itspace.kidsacademy.controller;

import am.itspace.kidsacademy.entity.Teacher;
import am.itspace.kidsacademy.service.CourseService;
import am.itspace.kidsacademy.service.PhotoService;
import am.itspace.kidsacademy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final PhotoService photoService;

    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/admin/teachers")
    public String teacherPage(@PageableDefault(size = 4, page = 1) Pageable page, ModelMap modelMap) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getPageSize());
        Page<Teacher> teachersPage = teacherService.findAll(pageable);
        modelMap.addAttribute("teachers", teachersPage);
        modelMap.addAttribute("courses", courseService.findAll(Pageable.unpaged()));

        int totalPages = teachersPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/teachers";
    }

    @GetMapping("/admin/addTeacher")
    public String addTeacherPage(ModelMap modelMap) {
        modelMap.addAttribute("courses", courseService.findAll(Pageable.unpaged()));
        return "admin/addTeacher";
    }


    @PostMapping("/admin/teacher/add")
    public String addTeacher(@ModelAttribute Teacher teacher, @RequestParam("picName") MultipartFile multipartFile) throws IOException {
        Teacher saved = teacherService.save(teacher);
        photoService.saveAll(saved, multipartFile);
        return "admin/teachers";
    }

    @GetMapping("/admin/teachers/{id}")
    public String teacherSingle(@PathVariable("id") int id, ModelMap modelMap) {
        Teacher byId = teacherService.findById(id);
        modelMap.addAttribute("teacher", byId);
        modelMap.addAttribute("courses", courseService.findAll(Pageable.unpaged()));
        return "admin/singleTeacher";
    }

    @GetMapping("/admin/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable("id") int id) {
        teacherService.delete(id);
        return "admin/teachers";
    }


    @GetMapping("/admin/teacher/update/{id}")
    public String updateTeacherPage(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("courses", courseService.findAll(Pageable.unpaged()));
        modelMap.addAttribute("teacher", teacherService.findById(id));

        return "admin/updateTeacher";
    }

    @PostMapping("/admin/updateTeacher")
    public String updateTeacher(@ModelAttribute Teacher teacher, @RequestParam("picName") MultipartFile multipartFile) throws IOException {
        teacherService.update(teacher, multipartFile);
        return "admin/teachers";
    }

}
