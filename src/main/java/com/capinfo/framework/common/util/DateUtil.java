package com.capinfo.framework.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zfj
 * @version 1.0
 * @ClassName: DateUtil
 * @Description: 取得系统时间及其时间格式的处理
 * @date 2014年8月22日 下午5:54:26
 */
public class DateUtil {

    /**
     * 字符串到日期的转换。
     *
     * @param str     字符串
     * @param pattern 日期样式
     * @return 转换后的日期
     */
    public static Date convertStr2Date(String str, String pattern) {
        if (str == null || "".equals(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(str.trim()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return calendar.getTime();
    }

    /**
     * 日期比较。
     *
     * @param first  日期
     * @param second 日期
     * @return 比较结果-1 0 1
     */
    public static int compareTo(Date first, Date second) {
        if (first == null || second == null)
            return 2;
        else
            return first.compareTo(second);
    }

    /**
     * 天数增加
     *
     * @param date 对象日期
     * @param day  日
     * @return 增加后的日期
     */
    public static Date addDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 分钟增加
     *
     * @param date   对象日期
     * @param minute 分钟
     * @return 增加后的日期
     */
    public static Date addMinute(Date date, int minute) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


    /**
     * 获取系统时间。
     *
     * @return 系统时间
     */
    public static Date getSystemDate() {
        return new Date();
    }

    /**
     * 获取系统时间
     *
     * @return 年月日
     */
    public static String getSystemTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(getSystemDate());
    }

    /**
     * 字符串到日期的转换。
     *
     * @param obj     字符串
     * @param pattern 日期样式
     * @return 转换后的日期
     */
    public static Date objToDate(Object obj, String pattern) {
        if (obj == null || "".equals(obj))
            return getSystemDate();

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(String.valueOf(obj).trim()));
        } catch (ParseException ex) {
            return getSystemDate();
        }
        return calendar.getTime();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
        }
        return 0;
    }

    public static String dateToStr(String formatString, Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat(formatString);
        return dateformat.format(date);
    }

    /**
     * 小时增加
     *
     * @param date 对象日期
     * @param hour 日
     * @return 增加后的日期
     */
    public static Date addHour(Date date, int hour) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 获取时间
     *
     * @param time 对象日期
     * @return 增加后的日期
     */
    public static Date getTime(String date, String time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (time == null || time == "") {
                time = date + " " + DateFormat.getTimeInstance().format(new Date());
                return df.parse(time);
            }
            time = date + " " + time;
            return df.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }
}
