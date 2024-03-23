package am.itspace.kidsacademy.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        return "user/home";
    }


    @GetMapping("/gallery")
    public String galleryPage() {
        return "user/gallery";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "user/contact";
    }

}