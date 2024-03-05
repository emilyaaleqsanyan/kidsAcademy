package am.itspace.kidsacademy.entity;

import am.itspace.kidsacademy.entity.enams.Currency;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courseName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;

    private int yearsOld;

    private int availableSeats;

    private double duration;

    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
