package am.itspace.kidsacademy.repository;

import am.itspace.kidsacademy.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
