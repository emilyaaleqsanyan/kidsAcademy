package am.itspace.kidsacademy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "kid")
public class kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String name;

    private String surname;

    private int age;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
