package am.itspace.kidsacademy.repository;

import am.itspace.kidsacademy.entity.Photo;
import am.itspace.kidsacademy.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Photo findByTeacher(Teacher teacher);


}
