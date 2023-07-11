package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat vreme = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    public static String toString(Date date) {
        return (date == null? "NULL" : format.format(date));
    }

    public static java.util.Date sqlDateToUtilDate(java.sql.Date date){
        return new java.util.Date(date.getTime());
    }

    public static String timeToString(Date date){
        return (date == null ? "NULL" : vreme.format(date));
    }

}
