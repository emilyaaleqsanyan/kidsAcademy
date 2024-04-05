package am.itspace.kidsacademy.repository;

import am.itspace.kidsacademy.entity.Photo;
import am.itspace.kidsacademy.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

   Optional<Photo> findByTeacher(Teacher teacher);


}
