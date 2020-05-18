package lt.vu.decorators;

import javax.enterprise.inject.Default;
import javax.inject.Named;

@Named
public class TheMostImportantMessage implements ImportantStuff{

    @Override
    public String importantMessage() {
        return "Really important note: student is pretty good";
    }
}