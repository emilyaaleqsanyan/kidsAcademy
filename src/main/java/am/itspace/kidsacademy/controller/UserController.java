package am.itspace.kidsacademy.controller;

import am.itspace.kidsacademy.entity.User;
import am.itspace.kidsacademy.entity.enums.UserType;
import am.itspace.kidsacademy.security.SpringUser;
import am.itspace.kidsacademy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @GetMapping("/user/register")
    public String userRegisterPage(@RequestParam(value = "msg", required = false)
                                   String msg, ModelMap modelMap) {
        if (msg != null && !msg.isEmpty()) {
            log.info("msg: {}", msg);
            modelMap.addAttribute("msg", msg);
        }
        return "user/loginPage";
    }

    @PostMapping("/user/register")
    public String userRegister(@ModelAttribute User user) {
        return userService.userRegister(user);
    }

    @GetMapping("/loginPage")
    public String loginPage(@AuthenticationPrincipal SpringUser springUser) {
        if (springUser == null) {
            log.info("User not logged in");
            return "user/loginPage";
        }
        log.info("User logged in" + " " + springUser.getUser().getEmail());
        return "redirect:/";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal SpringUser springUser) {
        User user = springUser.getUser();
        if (user.getUserType() == UserType.ADMIN) {
            log.info("Admin logged in");
            return "redirect:/admin/home";
        } else {
            log.info("logged in" + " " + springUser.getUser().getEmail());
            return "redirect:/";
        }
    }
}


