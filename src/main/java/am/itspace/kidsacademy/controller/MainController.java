package am.itspace.kidsacademy.controller;


import am.itspace.kidsacademy.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequiredArgsConstructor
public class MainController {
    private final PictureService pictureService;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        return "user/home";
    }


    @GetMapping("/gallery")
    public String userGalleryPage(@PageableDefault(size = 4, page = 1) Pageable page, ModelMap modelMap) {

        pictureService.findAll(page,modelMap);

        return "user/gallery";
    }


    @GetMapping("/contact")
    public String contactPage() {
        return "user/contact";
    }

    @GetMapping("/news")
    public String newsPage() {

        return "user/news";
    }

}