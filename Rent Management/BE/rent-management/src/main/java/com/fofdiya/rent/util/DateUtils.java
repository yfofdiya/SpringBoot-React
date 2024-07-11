package com.fofdiya.rent.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String convertDateToYYYYMMDDHHMMSSFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String addOneMonth(String date) {
        LocalDate originalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate incrementedDate = originalDate.plusMonths(1);
        return incrementedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String minusOneMonth(String date) {
        LocalDate originalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate decrementedDate = originalDate.minusMonths(1);
        return decrementedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
