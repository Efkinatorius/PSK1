package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Classes;
import lt.vu.persistence.ClassesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@RequestScoped
public class ClassesUC {
    @Inject
    private ClassesDAO classesDAO;

    @Getter
    @Setter
    private Classes classes = new Classes();

    @Getter
    private List<Classes> allClasses;

    @PostConstruct
    public void init() {
        loadAllClasses();
    }

    private void loadAllClasses(){
        this.allClasses = classesDAO.loadAll();
    }

    @Transactional
    public String createClass(){
        this.classesDAO.persist(classes);
        return "index?faces-redirect=true";
    }
}
