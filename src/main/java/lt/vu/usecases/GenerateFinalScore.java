package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Students;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.utilities.IGenerator;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SessionScoped
@Named
public class GenerateFinalScore implements Serializable {

    @Inject
    IGenerator generator;

    private CompletableFuture<Double> scoreGenerationTask = null;

    @LoggedInvocation
    public String generateFinalScore() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        scoreGenerationTask = CompletableFuture.supplyAsync(() -> generator.generateEvaluation());

        return "/student.xhtml?faces-redirect=true&studentsId=" + requestParameters.get("studentsId");
    }

    public boolean isGenerationRunning() {
        return scoreGenerationTask != null && !scoreGenerationTask.isDone();
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if(scoreGenerationTask == null) {
            return null;
        } else if (isGenerationRunning()) {
            return "Calculating students hard work";
        }
        return "Students final score is: " + scoreGenerationTask.get();
    }

}
