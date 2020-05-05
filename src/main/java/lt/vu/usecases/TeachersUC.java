package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Teacher;
import lt.vu.persistence.TeacherDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@RequestScoped
public class TeachersUC {

    @Inject
    private TeacherDAO teacherDAO;

    @Getter
    @Setter
    private Teacher teacher = new Teacher();

    @Getter
    private List<Teacher> allTeachers;

    @PostConstruct
    public void init(){
        loadAllTeachers();
    }

    private void loadAllTeachers(){
        this.allTeachers = teacherDAO.loadAll();
    }

    @Transactional
    public String createTeacher(){
        this.teacherDAO.persist(teacher);
        return "index?faces-redirect=true";
    }

}
