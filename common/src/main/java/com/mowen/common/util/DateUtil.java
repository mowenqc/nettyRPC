package com.mowen.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/***
 * desc  : com.mowen.nettylearn.common.util
 * author: mowen
 * create_time: 2019/5/31 8:15
 * project_name : mowen_parent
 */
public class DateUtil {

    /**
     * 日期格式yyyy-MM-dd字符串常量
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 日期格式yyyy-MM-dd HH:mm:ss字符串常量
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式yyyy-MM字符串常量
     */
    public static final String MONTH_FORMAT = "yyyy-MM";

    /**
     * 一个月时间大约的long型数字
     */
    public static final long MONTH_LONG = 2651224907l;

    /**
     * @return 当前时间
     */
    public static long now() {
        return System.currentTimeMillis();
    }

    /**
     * 根据日期拆分该月内最大的时间以及最小的时间
     *
     * @param date 待拆分的日期
     */
    public static Date[] splitMaxMinCalendarDate(Date date) {
        Date[] dateArray = new Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar startCalendar = (Calendar) calendar.clone();
        startCalendar.set(Calendar.DAY_OF_MONTH, startCalendar.getMinimum(Calendar.DAY_OF_MONTH));
        startCalendar.set(Calendar.HOUR_OF_DAY, startCalendar.getMinimum(Calendar.HOUR_OF_DAY));
        startCalendar.set(Calendar.MINUTE, startCalendar.getMinimum(Calendar.MINUTE));
        startCalendar.set(Calendar.SECOND, startCalendar.getMinimum(Calendar.SECOND));
        Calendar endCalendar = (Calendar) calendar.clone();
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        endCalendar.set(Calendar.HOUR_OF_DAY, endCalendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        endCalendar.set(Calendar.MINUTE, endCalendar.getActualMaximum(Calendar.MINUTE));
        endCalendar.set(Calendar.SECOND, endCalendar.getActualMaximum(Calendar.SECOND));
        dateArray[0] = startCalendar.getTime();
        dateArray[1] = endCalendar.getTime();
        return dateArray;
    }

    /**
     * 得到当前日期的前/后　beforeDays　天的日期数
     */
    public static String getDate(int beforeDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, beforeDays);
        String a = dateToString(c.getTime(), DATE_FORMAT);
        return a;
    }

    /**
     * 得到当前日期的前/后　beforeDays　天的日期数,格式自定
     */
    public static String getDate(int beforeDays, String dateFormat) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, beforeDays);
        String a = dateToString(c.getTime(), dateFormat);
        return a;
    }

    /**
     * 获取传入日期与当前日期相差的秒数
     */
    public static int getBetweenMillis(Date date) {
        if (date != null) {
            int betweenMilis = (int) (new Date().getTime() - date.getTime());
            return betweenMilis / 1000;
        }
        return -1;
    }

    /**
     * 获取连个日期之间的时间间隔秒数
     */
    public static int getBetweenMillis(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            int betweenMilis = (int) ((endDate.getTime() - startDate.getTime()) / 1000);
            return betweenMilis;
        }
        return -1;
    }

    /**
     * 将日期类型转换为yyyy-MM-dd字符串
     *
     * @return String
     */
    public static String dateToString(Date dateValue) {
        return dateToString(dateValue, DATETIME_FORMAT);
    }

    /**
     * 将日期类型转换为指定格式的字符串
     *
     * @return String
     */
    public static String dateToString(Date dateValue, String format) {
        if (dateValue == null || format == null) {
            return null;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(dateValue);
        }
    }

    /**
     * 将日期yyyy-MM-dd字符串转为日期类型，如果转换失败返回null
     *
     * @return Date
     */
    public static Date stringToDate(String stringValue) {
        return stringToDate(stringValue, DATE_FORMAT);
    }

    /**
     * 将指定日期格式的字符串转为日期类型，如果转换失败返回null
     *
     * @return Date
     */
    public static Date stringToDate(String stringValue, String format) {
        Date dateValue = null;
        if (stringValue != null && format != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                dateValue = dateFormat.parse(stringValue);

            } catch (ParseException ex) {
                dateValue = null;
            }
        }
        return dateValue;
    }

    /**
     * 获得当前年
     *
     * @return string
     */
    public static String getNowYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    /**
     * 获得当前月
     *
     * @return string
     */
    public static String getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return "0" + month;
        } else {
            return String.valueOf(month);
        }
    }

    /**
     * 获得当前日
     *
     * @return string
     */
    public static String getNowDay() {
        return dateToString(new Date(), "dd");

    }

    /**
     * 昨天
     */
    public static String getYestday() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);
        return dateToString(date.getTime(), "dd");

    }

    /**
     * 返回几个月前的Date类型
     *
     * @param monthCount 几个月
     * @return Date
     */
    public static Date getDateFront(int monthCount) {
        return new Date(Calendar.getInstance().getTimeInMillis() - MONTH_LONG
            * monthCount);
    }

    /**
     * 返回当前小时
     *
     * @return string
     */
    public static String getNowHour() {
        return dateToString(new Date(), "HH");
    }

    /**
     * 返回当前分钟
     *
     * @return string
     */
    public static String getNowMinute() {
        return dateToString(new Date(), "mm");
    }

    /**
     * 设置时间的日期值
     *
     * @return Date
     */
    public static Date setDate(String stringDate, int num) {
        if (stringDate != null) {
            Date date = stringToDate(stringDate, "yyyy-MM-dd");
            return setDate(date, num);
        } else {
            return null;
        }
    }

    /**
     * 设置时间的日期值
     *
     * @return Date
     */
    public static Date setDate(Date date, int num) {
        Date dateValue = null;
        Calendar c = null;
        if (date != null) {
            c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, num);
            dateValue = c.getTime();
        }
        return dateValue;
    }

    /**
     * 取得两个日期的时间间隔,相差的天数
     */
    public static int getDayBetween(Date d1, Date d2) {
        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();
        if (d1.before(d2)) {
            before.setTime(d1);
            after.setTime(d2);
        } else {
            before.setTime(d2);
            after.setTime(d1);
        }
        int days = 0;

        int startDay = before.get(Calendar.DAY_OF_YEAR);
        int endDay = after.get(Calendar.DAY_OF_YEAR);

        int startYear = before.get(Calendar.YEAR);
        int endYear = after.get(Calendar.YEAR);
        before.clear();
        before.set(startYear, 0, 1);

        while (startYear != endYear) {
            before.set(startYear++, Calendar.DECEMBER, 31);
            days += before.get(Calendar.DAY_OF_YEAR);
        }
        return days + endDay - startDay;
    }

    public static Date addDay(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

    /**
     * 计算两个日期相差的天数 （注意单位是天 忽略时分秒）
     */
    public static int getIntervalDays(Date before, Date after) {

        if (null == before || null == after) {
            throw new NullPointerException();
        }

        long intervalMilli = (after.getTime() / (24 * 60 * 60 * 1000))
            - (before.getTime() / (24 * 60 * 60 * 1000));

        return (int) intervalMilli;
    }


    public static int getIntervalMonths(Date before, Date after) {
        if (null == before || null == after) {
            throw new NullPointerException();
        }

        Calendar b = Calendar.getInstance();
        b.setTime(before);
        Calendar a = Calendar.getInstance();
        a.setTime(after);

        int monthbefore = b.get(Calendar.MONTH);
        int monthafter = a.get(Calendar.MONTH);

        int ys = a.get(Calendar.YEAR) - b.get(Calendar.YEAR);

        return monthafter + ys * 12 - monthbefore;
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    /**
     * 获取今天的格式化时间yyyy-mm-dd
     */
    public static Date getTodayDate() {
        String date = dateToString(new Date(), DATE_FORMAT);
        return stringToDate(date);
    }

    public static Date tomorowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        Date tomorow = calendar.getTime();
        String string = dateToString(tomorow, DATE_FORMAT);
        return stringToDate(string);
    }

    public static Date afterNowDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        return calendar.getTime();
    }

    public static Date beforeNowDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - day);
        return calendar.getTime();
    }
}
