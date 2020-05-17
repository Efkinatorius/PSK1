package lt.vu.utilities;

import lt.vu.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;

@ApplicationScoped
@Alternative
public class EvaluationGenerator implements IGenerator{

    @LoggedInvocation
    public Double generateEvaluation() {
        System.out.println("Evaluation generator");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        Double score = (double)Math.round(new Random().nextInt(10));
        return score;
    }
}
