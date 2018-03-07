package com.spring.boot.util;

import org.apache.commons.lang.StringUtils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/***
 * App服务的公用方法
 *
 * @author bruce
 *
 */
public class UtilHelper {

    public static final String module = UtilHelper.class.getName();
    public static final SimpleDateFormat DATETIMEFULLFORMATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATETIMEAPIFORMATE = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATEFORMATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIMEFORMATE = new SimpleDateFormat("HH:mm:ss");
    public static final String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int PAGE_SIZE = 10;
    private static int currentid = 1;

    public static Timestamp formatTime(String date, String time) {
        try {
            return new Timestamp(DATETIMEFULLFORMATE.parse(date + " " + time).getTime());
        } catch (ParseException e) {
            return null;
        }
    }
    /**
     * 获取系统当前时间（yyyy-MM-dd ）
     * @return
     */
    public static String getNowDateStr() {
        return DATEFORMATE.format(new java.util.Date());
    }

    /**
     * 获取系统当前时间（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String getNowTimeStr() {
        return DATETIMEFULLFORMATE.format(new java.util.Date());
    }

    public static Date toDate(String strDate) {
        try {
            java.util.Date date = DATETIMEFULLFORMATE.parse(strDate + " " + "00:00:00");
            return new Date(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Time toTime(String strTime) {
        try {
            java.util.Date date = DATETIMEAPIFORMATE.parse("1970-01-01 " + strTime);
            return new Time(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date nowDate() {
        java.util.Date now = new java.util.Date();
        String nowDateStr = DATEFORMATE.format(now);
        try {
            return new Date(DATEFORMATE.parse(nowDateStr).getTime());
        } catch (ParseException e) {
            return new Date(now.getTime());
        }
    }

    public static Time nowTime() {
        return new Time(new java.util.Date().getTime());
    }

    public static boolean dateEqual(Date firstDate, Date secondDate) {
        return DATEFORMATE.format(firstDate).equals(DATEFORMATE.format(secondDate));
    }

    public static boolean isToday(Date date) {
        return dateEqual(nowDate(), date);
    }

    public static int compareDate(Date firstDate, Date secondDate) {
        return DATEFORMATE.format(firstDate).compareTo((DATEFORMATE.format(secondDate)));
    }

    public static int compareTime(Time first, Time second) {
        return TIMEFORMATE.format(first).compareTo(TIMEFORMATE.format(second));
    }

    @SuppressWarnings("deprecation")
    public static String isHoliday(Date date) {
        int day = date.getDay();
        if (day == 0 || day == 6) {
            return "Y";
        } else {
            return "N";
        }
    }

    public static boolean isEmpty(String str) {
        if (StringUtils.isEmpty(str) || StringUtils.isBlank(str)) {
            return true;
        }
        return false;
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        if (UtilHelper.isEmpty(str)) {
            return false;
        } else {
            Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
            Matcher m = p.matcher(str);
            return m.matches();
        }
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 生成校验码
     *
     * @return 校验码
     */
    public static String getVerifyCode() {
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 9);
        return result;
    }


    private synchronized static int getNextId() {
        if (currentid++ > 999) {
            currentid = 1;
        }
        return currentid;
    }
}
