package com.donut.server.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 取得系统时间及其时间格式的处理
 * @author 赵峰剑
 * @date 2014年8月22日 下午5:54:26
 * @version 1.0
 */
public class DateUtil
{

    public static final String COMMEN_SEP_DATE_FORMAT = "yyyy/MM/dd";

    public static final String COMMEN_SPOT_DATE_FORMAT = "yyyy.MM.dd";

    public static final String COMMEN_SPOT_TIME_HH_MM_FORMAT = "yyyy.MM.dd HH:mm";

    public static final String COMMEN_TIME_HH_MM_FORMAT = "HH:mm";

    public static final String COMMEN_DATE_FORMAT = "yyyy-MM-dd";

    public static final String COMMEN_DATE_HOUR_FORMAT = "yyyy-MM-dd HH";

    public static final String COMMEN_DATE_TIME_HH_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String COMMEN_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static final String COMMEN_DATE_TIME_MINUITE_FORMAT = "yyyy-MM-dd HH:mm";

    private final static long minute = 60 * 1000;// 1分钟

    private final static long hour = 60 * minute;// 1小时

    private final static long day = 24 * hour;// 1天

    private final static long month = 31 * day;// 月

    private final static long year = 12 * month;// 年

    /**
     * 本函数用于获取当前时间
     * 
     * @param
     * @param
     * 
     * @return 返回当前的时间
     */
    public static Timestamp getThisDate() throws ParseException
    {
        // 获取当前时间
        Timestamp thisdate = new Timestamp(new Date().getTime());
        return thisdate;
    }

    public static String dateToString(String formatString, Date date)
    {
        SimpleDateFormat dateformat = new SimpleDateFormat(formatString);

        return dateformat.format(date);
    }

    /**
     * 字符串到日期的转换。
     * 
     * @param str
     *            字符串
     * @param pattern
     *            日期样式
     * @return 转换后的日期
     */
    public static Date convertString2Date(String str, String pattern)
    {
        if (str == null || "".equals(str))
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try
        {
            calendar.setTime(sdf.parse(str.trim()));
        }
        catch(ParseException ex)
        {
            ex.printStackTrace();
        }
        return calendar.getTime();
    }

    /**
     * 日期到字符串的转换。
     * 
     * @param date
     *            日期
     * @param pattern
     *            日期样式
     * @return 转换后的
     */
    public static String convertDate2String(Date date, String pattern)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    public static int compareTo(Date first, Date second)
    {
        if (first == null || second == null)
            return 2;
        else
            return first.compareTo(second);
    }

    /**
     * 年增加
     * 
     * @param date
     *            对象日期
     * @param year
     *            年
     * @return 增加后的日期
     */
    public static Date addYear(Date date, int year)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 月份增加
     * 
     * @param date
     *            对象日期
     * @param month
     *            月
     * @return 增加后的日期
     */
    public static Date addMonth(Date date, int month)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 天数增加
     * 
     * @param date
     *            对象日期
     * @param day
     *            日
     * @return 增加后的日期
     */
    public static Date addDay(Date date, int day)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 小时增加
     * 
     * @param date
     *            对象日期
     * @param hour
     *            日
     * @return 增加后的日期
     */
    public static Date addHour(Date date, int hour)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    public static Date convertMs2Date(Long millionSeconds)
    {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millionSeconds);
        return c.getTime();
    }

    /**
     * 分钟增加
     * 
     * @param date
     *            对象日期
     * @param minute
     *            分钟
     * @return 增加后的日期
     */
    public static Date addMinute(Date date, int minute)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 秒增加
     * 
     * @param date
     *            对象日期
     * @param seconds
     *            分钟
     * @return 增加后的日期
     */
    public static Date addSeconds(Date date, int seconds)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 获取系统时间。
     * 
     * @return 系统时间
     */
    public static Date getSystemDate()
    {
        return new Date();
    }

    /**
     * 日期判断
     * 
     * @param date
     *            对象字符
     * @param pattern
     *            日期时刻formart
     * @return 日期True、日期否False
     */
    public static boolean isValidDate(Object date, String pattern)
    {
        if (date == null || pattern == null)
        {
            return false;
        }

        if (date.toString().length() != pattern.length())
        {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try
        {
            sdf.setLenient(false);
            sdf.parse(String.valueOf(date));
        }
        catch(ParseException ex)
        {
            return false;
        }
        return true;
    }

    /**
     * 获取时间
     * 
     * @return hh:mm:sss
     */
    public static String getHHMMSSS()
    {
        return DateFormat.getTimeInstance().format(new Date());
    }

    /**
     * 获取当前月对应的总天数
     * 
     * @return hh:mm:sss
     */
    public static int getDays(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取当前月对应的总天数
     * 
     * @return hh:mm:sss
     */
    public static int getDay(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取年月日
     * 
     * @return 年月日
     */
    public static String getYMD(Date date, String pattern)
    {
        if (date == null)
            date = getSystemDate();

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String ymd = sdf.format(date);
        if (pattern.contains("/") || pattern.contains("-"))
            return ymd.substring(0, 10);
        else
            return ymd.substring(0, 8);
    }

    /**
     * 计算两个时间差
     * 
     * @return hh:mm:ss
     */
    public static String caculateDate(Date first, Date second)
    {
        String timeDiffer = null;
        long diff = first.getTime() - second.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        long hour = (diff / (60 * 60 * 1000) - days * 24);
        long min = ((diff / (60 * 1000)) - days * 24 * 60 - hour * 60);
        long s = (diff / 1000 - days * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (hour > 0)
        {
            timeDiffer = hour + ":" + min + ":" + s;
        }
        else
        {
            timeDiffer = min + ":" + s;
        }
        return timeDiffer;
    }

    /**
     * 计算两个时间差
     * 
     * @return hh:mm:ss
     */
    public static String caculateDateInSencond(Date first, Date second)
    {
        String timeDiffer = null;
        long diff = (first.getTime() - second.getTime()) / 1000;
        timeDiffer = Long.toString(diff);
        return timeDiffer;
    }

    public static String getTimeFormatText(Date date)
    {
        if (date == null)
        {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year)
        {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month)
        {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day)
        {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour)
        {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute)
        {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    // 获得一周前的日期
    public static String lastWeek()
    {
        Date date = new Date();

        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - 6;

        if (day < 1)
        {
            month -= 1;
            if (month == 0)
            {
                year -= 1;
                month = 12;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11)
            {
                day = 30 + day;
            }
            else if (month == 1 || month == 3 || month == 5 || month == 7
                    || month == 8 || month == 10 || month == 12)
            {
                day = 31 + day;
            }
            else if (month == 2)
            {
                if (year == 0 || (year % 4 == 0 && year != 0))
                    day = 29 + day;
                else
                    day = 28 + day;
            }
        }
        String y = year + "";
        String m = "";
        String d = "";
        if (month < 10)
            m = "0" + month;
        else
            m = month + "";
        if (day < 10)
            d = "0" + day;
        else
            d = day + "";

        return y + "-" + m + "-" + d;
    }

    // 获得一月前的日期
    public static String lastMonth()
    {
        Date date = new Date();

        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));

        if (month == 0)
        {
            year -= 1;
            month = 12;
        }
        else if (day > 28)
        {
            if (month == 2)
            {
                if (year == 0 || (year % 4 == 0 && year != 0))
                {
                    day = 29;
                }
                else
                    day = 28;
            }
            else if ((month == 4 || month == 6 || month == 9 || month == 11)
                    && day == 31)
            {
                day = 30;
            }
        }
        String y = year + "";
        String m = "";
        String d = "";
        if (month < 10)
            m = "0" + month;
        else
            m = month + "";
        if (day < 10)
            d = "0" + day;
        else
            d = day + "";

        return y + "-" + m + "-" + d;
    }

    public static String beforNumDay(Date date, int day)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, day);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 
     * 
     * @Title: dateDiff
     * @Description: 获取两个时间的天数间隔
     * @param @param date1
     * @param @param date2
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public static int dateDiff(Date date1, Date date2)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date1);
        String time2 = sdf.format(date2);
        try
        {
            date1 = sdf.parse(time);
            date2 = sdf.parse(time2);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }

        long d1 = date1.getTime();
        long d2 = date2.getTime();
        long dif = d1 > d2 ? d1 - d2 : d2 - d1;
        long count = dif / 86400000;// 获取两个时间的间隔天数
        return Integer.parseInt(String.valueOf(count));
    }

}
