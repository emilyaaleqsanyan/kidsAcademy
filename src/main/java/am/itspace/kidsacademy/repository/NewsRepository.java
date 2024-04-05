package am.itspace.kidsacademy.repository;

import am.itspace.kidsacademy.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Integer> {

}
