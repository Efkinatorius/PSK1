package lt.vu.usecases;

import lt.vu.decorators.ImportantStuff;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ImportantMessage {

    @Inject
    private ImportantStuff importantStuff;

    public String importantMessage() {
        return importantStuff.importantMessage();
    }
}
