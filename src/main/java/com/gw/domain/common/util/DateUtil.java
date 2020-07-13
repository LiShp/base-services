package com.gw.domain.common.util;

import com.gw.cloud.common.base.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author gwx
 * @description
 * @date 2020-05-18
 */

public class DateUtil {

    public static final String DEFAULT_TIME_ZONE_TYPE = "GMT+8";

    public static final String DEFAULT_FORMAT_PATTERN_DATETIME_MICR = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String DEFAULT_FORMAT_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_PATTERN_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_FORMAT_PATTERN_TIME = "HH:mm:ss";
    public static final String FORMAT_PATTERN_DATETIME_ONE = "yyyyMMddHHmmss";
    public static final String FORMAT_PATTERN_DATETIME_TWO = "yyyy/M/d HH:mm";
    public static final String FORMAT_PATTERN_DATE_ONE = "yyyyMMdd";
    public static final String FORMAT_PATTERN_DATE_TWO = "yyyy/M/d";
    public static final String FORMAT_PATTERN_TIME_ONE = "HHmmss";

    private DateUtil() {
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getFormatCurrentDate(String pattern) {
        if (StringUtil.isNullOrWhiteSpace(pattern)) {
            pattern = "yyyy-MM-dd";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(getCurrentDate());
    }

    private static Calendar initCalendar(Date date) {
        Calendar temCalendar = Calendar.getInstance();
        temCalendar.setTime(date);
        return temCalendar;
    }

    public static Date getEndOfMonth(Date date) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.set(5, temCalendar.getActualMaximum(5));
        return temCalendar.getTime();
    }

    public static Date afterDay(Date date, int days) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(5, days);
        return temCalendar.getTime();
    }

    public static Date afterMinute(Date date, int minute) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(12, minute);
        return temCalendar.getTime();
    }

    public static Date afterSecond(Date date, int second) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(13, second);
        return temCalendar.getTime();
    }

    public static Date dateStrToDate(String dateStr, String dateFormat) {
        if (StringUtil.isNullOrWhiteSpace(dateStr)) {
            return null;
        } else {
            if (StringUtil.isNullOrWhiteSpace(dateFormat)) {
                dateFormat = "yyyy-MM-dd HH:mm:ss";
            }

            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

            try {
                return sdf.parse(dateStr);
            } catch (ParseException var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static long dateStrToLong(String dateStr, String dateFormat) {
        Date date = dateStrToDate(dateStr, dateFormat);
        return date == null ? 0L : date.getTime();
    }

    public static String dateToString(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public static Date getDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormat.parse(dateFormat.format(date));
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static long getDatePeriod(Date start, Date end) {
        Date startDay = getDay(start);
        Date endDay = getDay(end);
        return startDay != null && endDay != null ? Math.abs((startDay.getTime() - endDay.getTime()) / 86400000L) : -1L;
    }

    public static long getTimePeriod(Date start, Date end) {
        return end.getTime() - start.getTime();
    }
}
