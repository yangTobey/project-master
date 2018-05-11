package com.spring.boot.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
    public static final SimpleDateFormat DATETIMEFORMATE = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat DATETIMEFULLFORMATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATETIMEAPIFORMATE = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATEFORMATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIMEFORMATE = new SimpleDateFormat("HH:mm:ss");
    public static final String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int PAGE_SIZE = 10;
    private static int currentid = 1;
    private static Calendar calendar = Calendar.getInstance();

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
     * 获取系统当前时间（yyyyMMddHHmmss）
     * @return
     */
    public static String getNowDateTimeStr() {
        return DATETIMEFORMATE.format(new java.util.Date());
    }

    /**
     * 获取系统当前时间（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String getNowTimeStr() {
        return DATETIMEFULLFORMATE.format(new java.util.Date());
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int getYear(){
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 获取当前日期是当年第几周
     * @return
     */
    public static int getWeekOfYear(){
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getMonth(){
        return calendar.get(Calendar.MONTH) + 1;
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
     * 将字符串转化为Double类型返回
     * @param str
     * @return
     */
    public static Double toDoubleNum(String str) {
        if (UtilHelper.isEmpty(str)) {
            return 0.00;
        }else{
            return Double.valueOf(str);
        }
    }
    /**
     * 将字符串转化为Integer类型返回
     * @param str
     * @return
     */
    public static Integer toIntegerNum(String str) {
        if (UtilHelper.isEmpty(str)) {
            return 0;
        }else{
            return Integer.valueOf(str);
        }
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
     * 利用正则表达式判断字符串是否是数字（不包含小数点）
     * @param str
     * @return
     */
    public static boolean isNumer(String str){
        if (UtilHelper.isEmpty(str)) {
            return false;
        }else{
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            if( !isNum.matches() ){
                return false;
            }
            return true;
        }
    }
    /**
     * 利用正则表达式判断字符串是否是两位小数(正整数、一位小数、两位小数)数字
     * @param str
     * @return
     */
    public static boolean isDoubleNumer(String str){
        if (UtilHelper.isEmpty(str)) {
            return false;
        }else{
            Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
            Matcher isNum = pattern.matcher(str);
            if( !isNum.matches() ){
                return false;
            }
            return true;
        }
    }

    /**
     * 两个数(整数)相除，保留四位小数返回（因需要计算百分比，需要保留四位小数,如：90.98%，小数为：0.9098）
     * @param numberOne
     * @param numberTwo
     * @return
     */
    public static String DecimalFormatNumber(Integer numberOne,Integer numberTwo){
        DecimalFormat df=new DecimalFormat("0.0000");
        if(numberTwo==null||numberTwo==0){
            return "0";
        }else if(numberOne==null){
            numberOne=0;
        }
        return df.format((float)numberOne/numberTwo);
    }
    /**
     * 两个数(整数)相除，保留两位小数返回
     * @param numberOne
     * @param numberTwo
     * @return
     */
    public static String decimalNumber(Integer numberOne,Integer numberTwo){
        DecimalFormat df=new DecimalFormat("0.00");
        if(numberTwo==null||numberTwo==0){
            return "0";
        }else if(numberOne==null){
            numberOne=0;
        }
        return df.format((float)numberOne/numberTwo);
    }
    /**
     * 两个数（小数）相除，保留四位小数返回（因需要计算百分比，需要保留四位小数,如：90.98%，小数为：0.9098）
     * @param numberOne
     * @param numberTwo
     * @return
     */
    public static String DecimalFormatDoubleNumber(Double numberOne,Double numberTwo){
        DecimalFormat df=new DecimalFormat("0.0000");
        if(numberTwo==null||numberTwo==0){
            return "0";
        }else if(numberOne==null){
            numberOne=0.00;
        }
        return df.format((Double) numberOne/numberTwo);
    }
    /**
     * 两个数（小数）相除，保留四位小数返回（因需要计算百分比，需要保留四位小数,如：90.98%，小数为：0.9098）
     * @param numberOne
     * @param numberTwo
     * @return
     */
    public static String DecimalFormatDoubleNumber(Double numberOne,Integer numberTwo){
        DecimalFormat df=new DecimalFormat("0.0000");
        if(numberTwo==null||numberTwo==0){
            return "0";
        }else if(numberOne==null){
            numberOne=0.00;
        }
        return df.format((Double) numberOne/numberTwo);
    }
    /**
     * 乘以100后保留2位小数返回
     * @param number
     * @return
     */
    public static Double DecimalFormatDouble(String number){
        DecimalFormat twoDouble=new DecimalFormat("0.00");
        if("0".equals(number)){
            return 0.00;
        }
        return Double.valueOf(twoDouble.format(Double.valueOf(number)*100));
    }
    /**
     * 保留2位小数返回
     * @param number
     * @return
     */
    public static Double DecimalFormatForDouble(String number){
        DecimalFormat twoDouble=new DecimalFormat("0.00");
        if("0".equals(number)){
            return 0.00;
        }
        return Double.valueOf(twoDouble.format(Double.valueOf(number)));
    }

    /**
     * 判断字符串是否只包含0-9，a-z,A-Z(用于校验账号或者密码)
     * @param str
     * @return
     */
    public static boolean matcherStr(String str){
        if(UtilHelper.isEmpty(str)){
            return  false;
        }
        //最多匹配18次，账号或者密码长度最大为18
        String regex = "([A-Z]|[a-z]|[0-9]){0,18}";
        return str.matches(regex);
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
