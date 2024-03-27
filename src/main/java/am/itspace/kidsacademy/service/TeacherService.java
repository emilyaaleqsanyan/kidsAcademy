package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface TeacherService {
    Teacher save(Teacher teacher);

    Page<Teacher> findAll(Pageable pageable);

    Teacher findById(int id);

    void delete(int teacherId);

    Teacher update(Teacher teacher, MultipartFile multipartFile) ;
}
