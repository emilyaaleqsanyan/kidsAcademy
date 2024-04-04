package am.itspace.kidsacademy.service;


import am.itspace.kidsacademy.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;




public interface TeacherService {
    Teacher save(Teacher teacher);

    Page<Teacher> findAll(Pageable page, ModelMap modelMap);


    Teacher findById(int id, ModelMap modelMap);

    void delete(int teacherId);

    void update(Teacher teacher, MultipartFile multipartFile);
}
