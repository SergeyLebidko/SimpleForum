package simpleforum.utilities;

import java.time.LocalDateTime;
import java.util.Map;

public class DateParametersExtractor {

    public static void extractToParams(LocalDateTime dateTime, Map<String, Object> params){
        params.put("year", dateTime.getYear());
        params.put("month", dateTime.getMonthValue());
        params.put("day", dateTime.getDayOfMonth());
        params.put("hour", dateTime.getHour());
        params.put("min", dateTime.getMinute());
        params.put("sec", dateTime.getSecond());
    }

}
