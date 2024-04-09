package am.itspace.kidsacademy.service.impl;


import am.itspace.kidsacademy.entity.Picture;

import am.itspace.kidsacademy.repository.PictureRepository;
import am.itspace.kidsacademy.service.PictureService;
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
public class PictureServiceImpl implements PictureService {


    @Value("${kidsAcademy1.picture.upload.directory}")
    private String uploadDirectory;


    private final PictureRepository pictureRepository;

    @Override
    public Page<Picture> findAll(Pageable page, ModelMap modelMap) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getPageSize());
        Page<Picture> picturePage = pictureRepository.findAll(pageable);
        modelMap.addAttribute("pictures", picturePage);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, picturePage.getTotalPages())
                .boxed()
                .toList();

        modelMap.addAttribute("pageNumbers", pageNumbers);


        return picturePage;
    }

    @Override
    public void delete(int pictureId) {
        Optional<Picture> pictureOptional = pictureRepository.findById(pictureId);
        if (pictureOptional.isPresent()) {
            Picture picture = pictureOptional.get();
            pictureRepository.deleteById(pictureId);
        }
    }

    @Override
    public void save(MultipartFile mFile) {
        if (mFile != null && !mFile.isEmpty()) {
            String picName = System.currentTimeMillis() + "_" + mFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            try {
                mFile.getBytes();
                mFile.transferTo(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
            pictureRepository.save(Picture.builder().pictureName(picName).build());
        }
    }
}