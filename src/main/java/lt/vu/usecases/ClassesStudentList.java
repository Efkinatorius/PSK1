package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Classes;
import lt.vu.entities.Students;
import lt.vu.persistence.ClassesDAO;
import lt.vu.persistence.StudentsDAO;

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
public class ClassesStudentList {

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private ClassesDAO classesDAO;

    @Getter
    @Setter
    private Students students = new Students();

    @Getter
    @Setter
    private Classes classes = new Classes();

    @Getter
    private List<Students> studentsList;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer classId = Integer.parseInt(requestParameters.get("classId"));
        this.classes = classesDAO.findOne(classId);
        loadClassesStudents(classId);
    }

    private void loadClassesStudents(Integer classId){
        this.studentsList = studentsDAO.loadAll(classId);
    }

    @Transactional
    public String createStudent(){
        students.setClasses(this.classes);
        studentsDAO.persist(students);
        return "classesStudents?faces-redirect=true&classId=" + this.classes.getId();
    }
}
