package com.ylsm.untils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class DateUtils {

    public static String format(String format, Date date) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String format(String format) {
        return format(format, new Date());
    }

    public static synchronized int getReloadDay(String last) {
        int i = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date lastDate = null;
        Date now = null;
        try {
            lastDate = dateFormat.parse(last);
            now = dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            log.debug("date format error! ");
        }
        Assert.notNull(lastDate, "date format error! lastDate is null!");

        for(GregorianCalendar calendar = new GregorianCalendar(); lastDate.compareTo(now) < 0; lastDate = calendar.getTime()) {
            ++i;
            calendar.setTime(lastDate);
            calendar.add(Calendar.DATE, 1);
        }

        return i;
    }

    public static boolean equalsDates(String now, List<String> date) {
        if (StringUtils.isBlank(now)) {
            return false;
        }
        for (String s : date) {
            if (now.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equalsDate(String now, String date) {
        return !Objects.isNull(now) && now.equals(date);
    }

    public static String getLastDayToString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return df.format(calendar.getTime());
    }

    public static String getLastDayToStringByString(String dataString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        try {
            Date date = df.parse(dataString);
            calendar.setTime(date);
            calendar.add(5, -1);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return df.format(calendar.getTime());
    }

}
