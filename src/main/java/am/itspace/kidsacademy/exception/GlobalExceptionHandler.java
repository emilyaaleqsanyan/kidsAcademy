package am.itspace.kidsacademy.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(value = IOException.class)
   public String ioExceptionHandler(Model theModel){
       theModel.addAttribute("err","IOException");

       return "admin/error";
   }
    @ExceptionHandler(value = FileNotFoundException.class)
    public String fileNotFoundExceptionHandler(Model model) {
       model.addAttribute("err","FileNotFoundException");
        return "admin/error";
    }
}
