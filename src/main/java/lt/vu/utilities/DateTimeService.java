package lt.vu.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeService {

    public String date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");

        Date date = new Date();
        return sdf.format(date);
    }
}
