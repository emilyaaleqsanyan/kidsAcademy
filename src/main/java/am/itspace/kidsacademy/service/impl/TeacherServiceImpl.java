package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.Photo;
import am.itspace.kidsacademy.entity.Teacher;
import am.itspace.kidsacademy.repository.PhotoRepository;
import am.itspace.kidsacademy.repository.TeacherRepository;
import am.itspace.kidsacademy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    @Value("${kidsAcademy1.picture.upload.directory}")
    private String uploadDirectory;
    private final TeacherRepository teacherRepository;
    private final PhotoRepository photoRepository;


    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Teacher findById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();
            teacherRepository.deleteById(teacherId);
        }
    }

    @Override
    public Teacher update(Teacher teacher, MultipartFile multipartFile) throws IOException {
        Teacher updated = findById(teacher.getId());

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            try {
                multipartFile.getBytes();
                multipartFile.transferTo(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
            photoRepository.save(Photo.builder()
                    .teacher(teacher)
                    .name(picName)
                    .path(uploadDirectory)
                    .build());

        } else teacher.setPhoto(teacher.getPhoto());
        return teacherRepository.save(teacher);
    }
}







