package am.itspace.kidsacademy.repository;

import am.itspace.kidsacademy.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
