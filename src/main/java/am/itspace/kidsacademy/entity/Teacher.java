package am.itspace.kidsacademy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "teacher")
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    @ManyToOne
    private Course course;

    @OneToOne
    private Photo photo;

    private String information;
}
