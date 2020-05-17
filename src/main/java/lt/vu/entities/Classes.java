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
import javax.persistence.Table;

@Entity
@NamedQueries({
        @NamedQuery(name = "classes.loadAll", query = "select cl from Classes cl")
})

@Table(name = "CLASSES")
@Getter @Setter
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Size(max = 5)
    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;

    @OneToMany(mappedBy = "classes")
    private List<Students> studentList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return Objects.equals(name, classes.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
