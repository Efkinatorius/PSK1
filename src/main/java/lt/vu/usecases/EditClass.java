package lt.vu.usecases;


import lombok.Setter;
import lombok.Getter;
import lt.vu.entities.Classes;
import lt.vu.entities.Students;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ClassesDAO;
import lt.vu.persistence.StudentsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class EditClass implements Serializable {

    @Getter @Setter
    private Classes classes;

    @Inject
    private ClassesDAO classesDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer classId = Integer.parseInt(requestParameters.get("classId"));
        this.classes = classesDAO.findOne(classId);
    }

    @Transactional
    @LoggedInvocation
    public String editInfo() {
        try{
            classesDAO.update(this.classes);
        } catch (OptimisticLockException e) {
            return "/editClassInfo.xhtml?faces-redirect=true&classId=" + this.classes.getId() + "&error=optimistic-lock-exception";
        }
        return "index.xhtml?faces-redirect=true";
    }
}
