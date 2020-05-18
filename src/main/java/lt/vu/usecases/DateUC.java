package lt.vu.usecases;

import lt.vu.utilities.DateTimeService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class DateUC {

    @Inject
    private DateTimeService dts;

    public String showDate(){
        return dts.date();

    }
}
