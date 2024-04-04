package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.News;
import org.springframework.ui.ModelMap;

import java.util.List;


public interface NewsService {


    News save(News news);

    List<News> findAll(News news, ModelMap modelMap);

    void delete(int newsId);


}
