package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Students;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.StudentsDAO;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@RequestScoped
@Named
@Getter @Setter
public class UpdateFinalScore implements Serializable {

    @Getter @Setter
    private Students students;

    @Inject
    private StudentsDAO studentsDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentsId = Integer.parseInt(requestParameters.get("studentsId"));
        this.students = studentsDAO.findOne(studentsId);
    }

    @Transactional
    @LoggedInvocation
    public String updateScore() {
        try{
            studentsDAO.update(this.students);
        } catch (OptimisticLockException e) {
            return "/student.xhtml?faces-redirect=true&studentsId=" + this.students.getId();// + "&error=optimistic-lock-exception";
        }
        return "index.xhtml?faces-redirect=true";
    }
}
