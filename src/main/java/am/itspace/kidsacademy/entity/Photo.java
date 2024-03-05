package am.itspace.kidsacademy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String path;

    @OneToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

}
