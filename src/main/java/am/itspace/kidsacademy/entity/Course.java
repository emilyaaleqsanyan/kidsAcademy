package am.itspace.kidsacademy.entity;

import am.itspace.kidsacademy.entity.enums.Currency;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courseName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    private int yearsOld;

    private int availableSeats;

    private double duration;

    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private CourseSchedule courseSchedule;


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", yearsOld=" + yearsOld +
                ", availableSeats=" + availableSeats +
                ", duration=" + duration +
                ", price=" + price +
                ", currency=" + currency +
                '}';
    }
}
