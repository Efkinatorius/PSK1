package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Students.findAll", query = "select st from Students as st where st.classes.id =: classId")
})

@Table(name = "STUDENTS")
@Getter @Setter
public class Students implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 30)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 30)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull(message = "please enter student code")
    @Column(name = "STUDENT_CODE")
    private Long studentCode;

    //@NotNull
    @ManyToOne
    @JoinColumn(name = "CLASS_GROUP_ID")
    private Classes classes;

    @ManyToMany
    @JoinTable(name = "STUDENT_SUBJECTS")
    private List<Subjects> subject_list = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return Objects.equals(studentCode, students.studentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCode);
    }
}
