package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.Photo;
import am.itspace.kidsacademy.entity.Teacher;
import am.itspace.kidsacademy.repository.CourseRepository;
import am.itspace.kidsacademy.repository.PhotoRepository;
import am.itspace.kidsacademy.repository.TeacherRepository;
import am.itspace.kidsacademy.service.CourseService;
import am.itspace.kidsacademy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    @Value("${kidsAcademy1.picture.upload.directory}")
    private String uploadDirectory;
    private final TeacherRepository teacherRepository;
    private final PhotoRepository photoRepository;
    private final CourseService courseService;
    private final CourseRepository courseRepository;


    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Page<Teacher> findAll(Pageable page, ModelMap modelMap) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getPageSize());
        Page<Teacher> teachersPage = teacherRepository.findAll(pageable);
        modelMap.addAttribute("teachers", teachersPage);
        modelMap.addAttribute("courses", courseService.findAll(Pageable.unpaged(), modelMap, -1));
        List<Integer> pageNumbers = IntStream.rangeClosed(1, teachersPage.getTotalPages())
                .boxed()
                .toList();

        modelMap.addAttribute("pageNumbers", pageNumbers);
        return teachersPage;
    }


    @Override
    public Teacher findById(int id, ModelMap modelMap) {
        Teacher byId = teacherRepository.findById(id).orElseThrow();
        modelMap.addAttribute("teacher", byId);
        modelMap.addAttribute("courses", courseRepository.findAll());
        return byId;
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
    public void update(Teacher teacher, MultipartFile multipartFile) {

        Optional<Teacher> updated = teacherRepository.findById(teacher.getId());
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
        teacherRepository.save(teacher);
    }
}







