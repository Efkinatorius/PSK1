package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Subjects;
import lt.vu.mybatis.dao.StudentsMapper;
import lt.vu.mybatis.dao.SubjectsMapper;
import lt.vu.mybatis.model.StudentSubjects;
import lt.vu.mybatis.model.Students;
import lt.vu.persistence.SubjectsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model
@RequestScoped
public class StudentSubjectList {

    @Inject
    private SubjectsDAO subjectsDAO;

    @Inject
    private SubjectsMapper subjectsMapper;

    @Inject
    private StudentsMapper studentsMapper;

    @Getter
    @Setter
    private Students students = new Students();

    @Getter
    @Setter
    private StudentSubjects studentSubjects = new StudentSubjects();

    @Getter
    @Setter
    private Subjects subjects = new Subjects();

    @Getter
    private List<Students> studentList;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer subjectsId = Integer.parseInt(requestParameters.get("subjectsId"));
        this.subjects = subjectsDAO.findOne(subjectsId);
        loadSubjectStudents(subjectsId);
    }

    private void loadSubjectStudents(Integer subjectsId){
        this.studentList = studentsMapper.selectStudentsBySubject(subjectsId);
    }

}
