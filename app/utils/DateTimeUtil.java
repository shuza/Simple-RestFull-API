package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    public static String getDateFormat() {
        return "yyyy-MM-dd";
    }

    public static SimpleDateFormat getSimpleDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static String getCurrentDate() {
        Date date = new Date();
        String currentDate = getSimpleDateFormat(getDateFormat()).format(date);
        return currentDate;
    }

}
