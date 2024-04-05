package am.itspace.kidsacademy.service.impl;


import am.itspace.kidsacademy.entity.News;
import am.itspace.kidsacademy.repository.NewsRepository;
import am.itspace.kidsacademy.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;


    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public List<News> findAll(News news, ModelMap modelMap) {
        modelMap.addAttribute("news", newsRepository.findAll());
        return newsRepository.findAll();
    }

    @Override
    public void delete(int newsId) {
        newsRepository.deleteById(newsId);
    }


}
