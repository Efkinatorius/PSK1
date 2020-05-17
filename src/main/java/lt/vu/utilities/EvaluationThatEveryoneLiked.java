package lt.vu.utilities;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;

@ApplicationScoped
@Alternative
public class EvaluationThatEveryoneLiked implements IGenerator{

    public Double generateEvaluation() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        Double score = (double)Math.round(10);
        return score;
    }
}
