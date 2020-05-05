package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.SubjectsDAO;
import lt.vu.entities.Subjects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@RequestScoped
public class SubjectsUC {
    @Inject
    private SubjectsDAO subjectsDAO;

    @Getter
    @Setter
    private Subjects subjects = new Subjects();

    @Getter
    private List<Subjects> allSubjects;

    @PostConstruct
    public void init() {
        loadAllSubjects();
    }

    private void loadAllSubjects() {
        this.allSubjects = subjectsDAO.loadAll();
    }

    @Transactional
    public String createSubjects(){
        this.subjectsDAO.persist(subjects);
        return "index?faces-redirect=true";
    }
}
