package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Classes;
import lt.vu.persistence.ClassesDAO;
import lt.vu.persistence.StudentsDAO;
import lt.vu.persistence.TeacherDAO;
import lt.vu.entities.Teacher;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
@RequestScoped
public class TeacherUC {

    @Inject
    private TeacherDAO teacherDAO;

    @Inject
    private ClassesDAO classesDAO;

    @Getter
    @Setter
    private Teacher teacher = new Teacher();

    @Getter
    @Setter
    private Classes classes = new Classes();

    @Getter
    private List<Teacher> teachers;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer classId = Integer.parseInt(requestParameters.get("classId"));
        this.classes = classesDAO.findOne(classId);
        loadTeacher(classId);
    };

    private void loadTeacher(Integer classId){ this.teachers = teacherDAO.loadAll(classId); }

    @Transactional
    public String addTeacher() {
        teacher.setClasses(this.classes);
        this.teacherDAO.persist(teacher);
        return "classesStudents?faces-redirect=true";
    }
}
