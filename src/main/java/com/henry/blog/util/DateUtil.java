package com.henry.blog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Laughing_Vzr on 2015/4/26.
 */
public class DateUtil {
    public static Long transferStringDateToLong(String formatDate, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        Date dt = sdf.parse(date);
        return dt.getTime();
    }

    public static String transferDateToString(String formatDate, Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        Date dt = new Date(date);
        String frmatDate = sdf.format(dt);
        return frmatDate;
    }
}
