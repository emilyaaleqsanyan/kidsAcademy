package am.itspace.kidsacademy.entity;


import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Table(name = "teacher")
@Getter
@Setter

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    @JoinColumn(name = "pic_name")
    private Photo photo;
    private String information;


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}
