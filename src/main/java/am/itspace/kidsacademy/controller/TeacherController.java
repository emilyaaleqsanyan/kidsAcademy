package am.itspace.kidsacademy.controller;


import am.itspace.kidsacademy.entity.Teacher;
import am.itspace.kidsacademy.exception.ProblemFoundException;
import am.itspace.kidsacademy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
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



@Controller
@RequiredArgsConstructor
public class TeacherController {

    @Value("${kidsAcademy1.picture.upload.directory}")
    private String uploadDirectory;

    private final TeacherService teacherService;


    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) {
        File file = new File(uploadDirectory, picName);
        try {
            if (file.exists()) {
                return IOUtils.toByteArray(new FileInputStream(file));
            }
        } catch (FileNotFoundException ex) {
     throw  new ProblemFoundException();
        } catch (IOException ex) {
         throw new ProblemFoundException();
        }
        return new byte[0];
    }


    @GetMapping("/teachers")
    public String teachersPage(@PageableDefault(size = 4, page = 1) Pageable page, ModelMap modelMap) {
        teacherService.findAll(page, modelMap);
        return "user/teachers";
    }


    @GetMapping("/teachers/{id}")
    public String teacherSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Teacher byId = teacherService.findById(id,modelMap);
        if (byId == null) {
            return "redirect:user/teachers";
        }
        modelMap.addAttribute("teacher", byId);

        return "user/singleTeacher";
    }


}
