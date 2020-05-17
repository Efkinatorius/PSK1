package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "subjects.loadAll", query = "select subj from Subjects as subj")
})

@Table(name = "SUBJECTS")
@Getter @Setter
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Size(max = 20)
    @Column(name = "SUBJECT_NAME")
    private String subject;

    @ManyToMany(mappedBy ="subject_list")
    private List<Students> studentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subjects subjects = (Subjects) o;
        return Objects.equals(subject, subjects.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }

}
