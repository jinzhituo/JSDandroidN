package jsjh.king.com.jsdandroidn.utils;

import android.content.ContentResolver;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import jsjh.king.com.jsdandroidn.global.MyApplication;


/**
 * Created by Megumi on 2016/7/10.
 */
public class DateUtil {

    private static String mYear; // 当前年
    private static String mMonth; // 月
    private static String mDay;
    private static String mWay;

    /**
     * 判断是否是今年
     *
     * @param date
     * @return
     */
    public static boolean isThisYear(String date) {
        boolean b = false;
        Date time = toDate(date);
        Date today = new Date();
        if (time != null) {
            String year = dateFormater.get().format(time).substring(0, 4);
            String thisyear = dateFormater.get().format(today).substring(0, 4);
            if (year.equals(thisyear)) {
                b = true;
            }
        }

        return b;
    }

    /**
     * 显示第几天
     *
     * @param sdate
     * @return
     */
    public static int showDate(String sdate) {
        int day = 0;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            long different = time.getTime() - today.getTime();
            day = new Long(different / (1000 * 60 * 60 * 24)).intValue();
        } else {
            day = Integer.MAX_VALUE;
        }
        return day;
    }

    /**
     * 和当前比比较相隔了多久
     *
     * @param time
     * @return
     */
    public static String getHowLongTime(long time) {
        Date today = new Date();
        long different = today.getTime() - time;
        return convertTime(different);

    }

    /**
     * 把相隔时间转为字符串
     *
     * @param time
     * @return
     */
    private static String convertTime(long time) {
        long hour = 0;
        long minute = 0;
        long second = 0;
        if (time <= 0) {
            return "";
        } else {
            second = time / 1000;
            if (second < 60) {
                return "0分钟前";
            } else {
                minute = second / 60;
                if (minute < 60) {
                    return minute + "分钟前";
                } else {
                    hour = minute / 60;
                    return hour + "小时前";
                }
            }
        }
    }

    /**
     * 根据不同的格式把时间戳转成日期字符串
     *
     * @param date
     * @param parsePattern
     * @return
     */
    public static String getStringByLong(long date, String parsePattern) {
        String dateStr = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(parsePattern);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        dateStr = dateFormat.format(date);
        return dateStr;
    }

    /**
     * 根据指定的日期字符串（格式：yyyy-MM-dd）获取指定的date对象
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 日期字符串转时间戳
     *
     * @param dateStr
     * @param dateformat
     * @return
     */
    public static Long dateToTimeStamp(String dateStr, String dateformat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateformat);
            Date date = format.parse(dateStr);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 根据日期和时间格式获取年龄
     *
     * @param dateStr
     * @param dateformat
     * @return
     */
    public static int getAge(String dateStr, String dateformat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateformat);
            Date birthDay = format.parse(dateStr);
            Calendar cal = Calendar.getInstance();

            if (cal.before(birthDay)) {
                throw new IllegalArgumentException(
                        "The birthDay is before Now.It's unbelievable!");
            }

            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH);
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(birthDay);

            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    //monthNow==monthBirth
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    } else {
                        //do nothing
                    }
                } else {
                    //monthNow>monthBirth
                    age--;
                }
            } else {
                //monthNow<monthBirth
                //donothing
            }

            return age;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }

    /**
     * 获取当前日期几月几号
     */
    public static String getDateString() {


        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mMonth + "月" + mDay + "日";
    }

    /**
     * 得到当年当月的最大日期
     **/
    public static int MaxDayFromDay_OF_MONTH(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }

    /**
     * 获取当前年月日
     *
     * @return
     */
    public static String StringData() {


        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mYear + "-" + (mMonth.length() == 1 ? "0" + mMonth : mMonth) + "-" + (mDay.length() == 1 ? "0" + mDay : mDay);
    }

    /**
     * 根据当前日期获得是星期几
     *
     * @return
     */
    public static String getWeek(String time) {
        String Week = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {


            c.setTime(format.parse(time));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "周天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "周六";
        }
        return Week;
    }

    /**
     * 获取今天往后一周的日期（几月几号）
     */
    public static List<String> getSevendate() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));


        for (int i = 0; i < 7; i++) {
            mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码
            if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (i + 1))) {
                mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (i + 1)));
            }
            String date = mMonth + "月" + mDay + "日";
            dates.add(date);
        }
        return dates;
    }

    /**
     * 获取今天往后一周的集合
     */
    public static List<String> get7week(Date date) {
        String week = "";
        List<String> weeksList = new ArrayList<String>();
        List<String> dateList = get7date(date);
        for (String s : dateList) {
            if (s.equals(StringData())) {
                week = "今天";
            } else {
                week = getWeek(s);
            }
            weeksList.add(week);
        }
        return weeksList;
    }

    /**
     * 七天日期字符串的集合
     *
     * @param date
     * @return
     */
    public static List<String> get7date(Date date) {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sim = new SimpleDateFormat(
                "yyyy-MM-dd");
        String dateStr = sim.format(date.getTime());
        dates.add(dateStr);
        for (int i = 0; i < 6; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            dateStr = sim.format(c.getTime());
            dates.add(dateStr);
        }
        return dates;
    }

    /**
     * 倒计时时间换算
     *
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 24) {
                    return "24:59:59";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";


    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
//            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
            return "今天";
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
//            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
            return "今天";
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
//            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
            return "今天";
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    /**
     * 获取当天0点时间戳
     *
     * @return
     */
    public static String getCurrentZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return String.valueOf(calendar.getTimeInMillis());
    }

    /**
     * 获取系统时间戳
     *
     * @return
     */
    public static String getCurrentDate() {
        long l = System.currentTimeMillis() / 1000;
        return String.valueOf(l);
    }

    /**
     * 获取系统时间戳
     *
     * @return
     */
    public static Long getCurrentDateL() {
        long l = System.currentTimeMillis() / 1000;
        return l;
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getCurrentDateY(String pattern) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String dateString = "";
        try {
            calendar.add(calendar.DATE, 0);//把日期往后增加一天.整数往后推,负数往前移动
            date = calendar.getTime(); //这个时间就是日期往后推一天的结果
            dateString = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
//        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
//        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//        String str = formatter.format(curDate);
//        return str;
    }

    /**
     * 获取系统时间24小时
     *
     * @return
     */
    public static String getCurrentDateY() {
        ContentResolver cv = MyApplication.getContext().getContentResolver();
        String strTimeFormat = android.provider.Settings.System.getString(cv,
                android.provider.Settings.System.TIME_12_24);
        if (strTimeFormat.equals("24")) {
            Log.i("activity", "24");
        }
        return strTimeFormat;
    }

    /*
    * 时间戳转换成字符窜
    * */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /*
    * 时间戳转换成字符窜
    * */
    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(date);
    }

    /*
    * 时间戳转换成日期
    * */
    public static Date getTimeToDate(String milSecond) {
        Date date = new Date(Long.valueOf(milSecond)*1000);
        return date;
    }

    /*
    * 将字符串转为时间戳
    * */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    // 将字符串转为时间戳
    public static String getTime(String dateString, String pattern) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d;
        try {
            d = sdf.parse(dateString);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return re_time;
    }

    //把日期转为字符串
    public static String ConverToString(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    //把字符串转为日期
    public static Date ConverToDate(String strDate, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.parse(strDate);
    }

    //时间戳转换日历
    public static Calendar TimeToCalendar(String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(time)*1000L);
        return calendar;
    }

    /**
     * 根据提供的年月日获取该月份的第一天
     *
     * @param date
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:26:57
     */
    public static String getSupportBeginDayofMonth(Date date) {
        date.getTime();
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        Date firstDate = startDate.getTime();
        return (firstDate.getTime() + "").substring(0, 10);

    }

    /**
     * 根据提供的年月获取该月份的最后一天
     *
     * @param date
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:29:38
     */
    public static String getSupportEndDayofMonth(Date date) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        startDate.set(Calendar.HOUR_OF_DAY, 23);
        startDate.set(Calendar.MINUTE, 59);
        startDate.set(Calendar.SECOND, 59);
        startDate.set(Calendar.MILLISECOND, 999);
        Date firstDate = startDate.getTime();
        return (firstDate.getTime() + "").substring(0, 10);
    }
}
