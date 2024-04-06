package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.Photo;
import am.itspace.kidsacademy.entity.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


public interface PhotoService {

    Photo save(Photo photo);

    Optional<Photo> findByTeacher(Teacher teacher);

    void saveAll(Teacher teacher, MultipartFile multipartFile);


}
