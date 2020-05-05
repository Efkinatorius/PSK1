package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Students;
import lt.vu.mybatis.dao.StudentSubjectsMapper;
import lt.vu.mybatis.dao.SubjectsMapper;
import lt.vu.mybatis.model.StudentSubjects;
import lt.vu.mybatis.model.Subjects;
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
public class StudentInformation {

    @Inject
    private StudentsDAO studentsDAO;

    @Getter
    @Setter
    private Students students = new Students();

    @Inject
    private SubjectsMapper subjectsMapper;

    @Inject
    private StudentSubjectsMapper studentSubjectsMapper;

    @Getter
    @Setter
    private StudentSubjects studentSubjects = new StudentSubjects();

    @Getter
    @Setter
    private Subjects subjects = new Subjects();

    @Getter
    private List<Subjects> subjectsList;

    @Getter
    private List<Subjects> subjectsListFree;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentsId = Integer.parseInt(requestParameters.get("studentsId"));
        this.students = studentsDAO.findOne(studentsId);
        loadStudents(studentsId);
        loadStudentsSubjects(studentsId);
        loadStudentsFreeSubjects(studentsId);
    }

    private void loadStudentsSubjects(Integer studentsId){
        this.subjectsList = subjectsMapper.selectSubjectsByStudents(studentsId);
    }

    private void loadStudentsFreeSubjects(Integer studentsId){
        this.subjectsListFree = subjectsMapper.selectSubjectsByStudentsFree(studentsId);
    }

    private void loadStudents(Integer studentsId) {
        this.students = studentsDAO.findOne(studentsId);
    }

    @Transactional
    public String addSubject(){
        studentSubjects.setStudentlistId(this.students.getId());
        studentSubjects.setSubjectListId(this.subjects.getId());
        studentSubjectsMapper.insert(studentSubjects);
        return "children?faces-redirect=true&studentsId=" + this.students.getId();
    }
}
