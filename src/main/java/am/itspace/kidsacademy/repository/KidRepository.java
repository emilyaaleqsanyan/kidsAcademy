package am.itspace.kidsacademy.repository;

import am.itspace.kidsacademy.entity.Kid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidRepository extends JpaRepository<Kid, Integer> {
}
