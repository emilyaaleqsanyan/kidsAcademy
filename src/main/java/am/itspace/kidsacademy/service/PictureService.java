package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    Page<Picture> findAll(Pageable pageable, ModelMap modelMap);


    void save(MultipartFile mFile);

    void delete(int pictureId);
}
