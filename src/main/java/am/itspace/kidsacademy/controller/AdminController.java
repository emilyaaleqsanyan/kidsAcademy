package am.itspace.kidsacademy.controller;

import am.itspace.kidsacademy.entity.News;
import am.itspace.kidsacademy.entity.Teacher;
import am.itspace.kidsacademy.service.CourseService;
import am.itspace.kidsacademy.service.NewsService;
import am.itspace.kidsacademy.service.PhotoService;
import am.itspace.kidsacademy.service.PictureService;
import am.itspace.kidsacademy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

@Controller
@RequiredArgsConstructor
public class AdminController {
    @Value("${kidsAcademy1.picture.upload.directory}")
    private String uploadDirectory;


    private final CourseService courseService;
    private final TeacherService teacherService;
    private final PhotoService photoService;
    private final PictureService pictureService;
    private final NewsService newsService;

    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/admin/teachers")
    public String teacherPage(@PageableDefault(size = 4, page = 1) Pageable page,
                              ModelMap modelMap) {
        teacherService.findAll(page, modelMap);
        return "admin/teachers";
    }

    @GetMapping("/admin/addTeacher")
    public String addTeacherPage(ModelMap modelMap) {
        courseService.findAll(Pageable.unpaged(), modelMap, -1);
        return "admin/addTeacher";
    }

    @PostMapping("/admin/teacher/add")
    public String addTeacher(@ModelAttribute Teacher teacher, @RequestParam("picName") MultipartFile multipartFile) {
        teacherService.save(teacher);
        photoService.saveAll(teacher, multipartFile);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/admin/teachers/{id}")
    public String teacherSingle(@PathVariable("id") int id, ModelMap modelMap, Pageable page) {
        courseService.findAll(Pageable.unpaged(), modelMap, id);
        return "admin/singleTeacher";
    }

    @GetMapping("/admin/teacher/update/{id}")
    public String updateTeacherPage(@PathVariable("id") int id, ModelMap modelMap) {
        courseService.findAll(Pageable.unpaged(), modelMap, id);
        return "admin/updateTeacher";
    }

    @GetMapping("/admin/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable("id") int id) {
        teacherService.delete(id);
        return "redirect:/admin/teachers";
    }

    @PostMapping("/admin/updateTeacher")
    public String updateTeacher(@ModelAttribute Teacher teacher, @RequestParam("picName") MultipartFile multipartFile, ModelMap modelMap) throws IOException {
        teacherService.update(teacher, multipartFile);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/admin/gallery")
    public String galleryPage(@PageableDefault(size = 4, page = 1) Pageable page, ModelMap modelMap) {
        pictureService.findAll(page, modelMap);
        return "admin/gallery";
    }

    @GetMapping("/admin/addPicture")
    public String addPicturePage() {
        return "admin/addPicture";
    }

    @PostMapping("/admin/picture/add")
    public String addPicture(@RequestParam("picName") MultipartFile mFile) {
        pictureService.save(mFile);
        return "redirect:/admin/gallery";
    }

    @GetMapping("/admin/picture/delete/{id}")
    public String deletePicture(@PathVariable("id") int id) {
        pictureService.delete(id);
        return "redirect:/admin/gallery";
    }

    @GetMapping("/admin/news")
    public String newsPage(ModelMap modelMap, @ModelAttribute News news) {
        newsService.findAll(news, modelMap);
        return "admin/news";
    }


    @GetMapping("/admin/addNews")
    public String addNewsPage() {
        return "admin/addNews";
    }

    @PostMapping("/admin/News/add")
    public String addNews(@ModelAttribute News news) {
        newsService.save(news);
        return "redirect:/admin/news";
    }

    @GetMapping("/admin/news/delete/{id}")
    public String deleteNews(@PathVariable("id") int id) {
        newsService.delete(id);
        return "redirect:/admin/news";
    }
}