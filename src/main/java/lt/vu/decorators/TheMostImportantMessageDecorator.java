package lt.vu.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.inject.Named;

@Decorator
public class TheMostImportantMessageDecorator implements ImportantStuff {

    @Inject
    @Delegate
    @Any
    TheMostImportantMessage importantStuff;

    @Override
    public String importantMessage() {
        String message = importantStuff.importantMessage();
        return message + " I mean he's insane";
    }
}
