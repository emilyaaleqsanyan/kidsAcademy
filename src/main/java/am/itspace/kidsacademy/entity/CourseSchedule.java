package am.itspace.kidsacademy.entity;

import am.itspace.kidsacademy.entity.enums.Day;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;

import java.util.List;

import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;


@Entity
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "courseSchedule", cascade = CascadeType.ALL)
    private List<Course> courses;

    @Override
    public String toString() {
        return "CourseSchedule{" +
                "id=" + id +
                ", courseStart=" + courseStart +
                ", courseEnd=" + courseEnd +
                ", day=" + day +
                '}';
    }
}
