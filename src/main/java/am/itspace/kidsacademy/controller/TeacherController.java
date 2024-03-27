package am.itspace.kidsacademy.controller;


import am.itspace.kidsacademy.entity.Teacher;
import am.itspace.kidsacademy.service.CourseService;
import am.itspace.kidsacademy.service.PhotoService;
import am.itspace.kidsacademy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor
public class TeacherController {

    @Value("${kidsAcademy1.picture.upload.directory}")
    private String uploadDirectory;

    private final TeacherService teacherService;
    private final CourseService courseService;
    private final PhotoService photoService;


    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) {
        File file = new File(uploadDirectory, picName);
        try {
            if (file.exists()) {
                return IOUtils.toByteArray(new FileInputStream(file));
            }
        } catch (FileNotFoundException  e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/teachers")
    public String teachersPage(@PageableDefault(size = 4, page = 1) Pageable page, ModelMap modelMap) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getPageSize());
        Page<Teacher> teachersPage = teacherService.findAll(pageable);
        modelMap.addAttribute("teachers", teachersPage);
        modelMap.addAttribute("courses", courseService.findAll(pageable));

        int totalPages = teachersPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "user/teachers";
    }


    @GetMapping("/teachers/{id}")
    public String teacherSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Teacher byId = teacherService.findById(id);
        if (byId == null) {
            return "redirect:user/teachers";
        }
        modelMap.addAttribute("teacher", byId);
        modelMap.addAttribute("courses", courseService.findAll(Pageable.unpaged()));
        return "user/singleTeacher";
    }
}
