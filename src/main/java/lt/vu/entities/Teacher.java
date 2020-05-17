package lt.vu.entities;

import com.sun.jmx.remote.internal.ArrayQueue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Table;

@Entity
@NamedQueries({
        @NamedQuery(name = "teacher.findAll", query = "select teacher from Teacher as teacher where teacher.classes.id =: classId")
})

@Table(name = "TEACHER")
@Getter @ Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @NotNull(message = "please enter teacher personal code")
    @Size(max = 20)
    @Column(name = "PERSONAL_CODE")
    private Long personalCode;

    @Size(max = 30)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 30)
    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToOne(mappedBy = "teacher")
    private Classes classes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(personalCode, teacher.personalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalCode);
    }
}
