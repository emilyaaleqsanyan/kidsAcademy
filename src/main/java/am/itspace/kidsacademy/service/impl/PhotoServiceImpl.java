package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.Photo;
import am.itspace.kidsacademy.entity.Teacher;
import am.itspace.kidsacademy.repository.PhotoRepository;
import am.itspace.kidsacademy.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Value("${kidsAcademy1.picture.upload.directory}")
    private String uploadDirectory;

    @Override
    public Photo save(Photo photo) {

        return photoRepository.save(photo);
    }

    @Override
    public Photo findByTeacher(Teacher teacher) {
        return photoRepository.findByTeacher(teacher);
    }

    @Override
    public void saveAll(Teacher teacher, MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {

            String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            try {
                multipartFile.getBytes();
                multipartFile.transferTo(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
            save(Photo.builder()
                    .teacher(teacher)
                    .name(picName)
                    .path(uploadDirectory)
                    .build());
        }
    }
}




