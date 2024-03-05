package am.itspace.kidsacademy.entity;

import am.itspace.kidsacademy.entity.enams.Day;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "course_schedule")
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime courseStart;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime courseEnd;

    @Enumerated(EnumType.STRING)
    private Day day;

    @OneToOne
    private Course course;
}
