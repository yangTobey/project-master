package com.spring.boot;

import com.spring.boot.util.UtilHelper;
import org.apache.shiro.crypto.hash.Hash;
import org.thymeleaf.expression.Lists;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/3/19.
 */
public class Test {
    public static void main(String[] args) {
        float num= (float)2/3;
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数
        String s = df.format(num);//返回的是String类型
        String s1 = df.format(0.555541);//返回的是String类型
        String s2 = df.format((float)20/1024);//返回的是String类型
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(Double.valueOf(s2)*100);
        System.out.println(Double.valueOf(s2));
        BigDecimal bigd = new BigDecimal((float)20/21.22);
        System.out.println(bigd);
        System.out.println(bigd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
        System.out.println(System.currentTimeMillis());
        String aa="2.3111";
        System.out.println(Double.valueOf(aa));


        System.out.println(UtilHelper.getWeekOfYear());



        String b=new StringBuffer("a1a").append("php").toString();
        System.out.println(b.intern()==b);
        String a=new StringBuffer("j").append("ava").toString();
        System.out.println(a.intern()==a);

        String arraryStringList="/a/b/a.txt,20;/b/q.exe,20;";
        String[] permsCompanyIdArray = arraryStringList.substring(0, arraryStringList.length()).split(";");
        System.out.println("长度："+permsCompanyIdArray.length);
        for(String list:permsCompanyIdArray){
            System.out.println(list);
        }


        String regex = "([A-Z]|[a-z]|[0-9]){0,18}";
        System.out.println("af1111111111111111".matches(regex));
        System.out.println("a454545aaaaa".matches(regex));
        System.out.println("a_-1+".matches(regex));

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("type",100);
        map=new HashMap<String,Object>();
        for(int i=0;i<=5;i++){
            map.put("type"+i, i );
        }
        for(int i=0;i<=5;i++){
            System.out.println(map.get("type"+i));
        }

        System.out.println(map.get("type"));

        String mes="asas；；,adad;qeqeq;qeqe;,AAAqe";
        System.out.println(mes.replace(",","").replace(";","").replace("，","").replace("；",""));

        String str="11;2;3";
        boolean status = str.contains("1;");
        List<String> list= Arrays.asList(str.split(";"));

        System.out.println(list.contains(String.valueOf(11L)));



        int year=0;
        int month=0;
        int day=10;
        Date startTime=null;
        try {
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             startTime = sDateFormat.parse("2018-02-10 10:10:10");
        }catch (Exception e){
            e.printStackTrace();
        }
        //获取当前月的天数
        int dayOfMonth=startTime.getDate();
        int nowYear=startTime.getYear()+1900;
        int nowMonth=startTime.getMonth()+1;
        //每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。
        if(dayOfMonth<day){
            if(nowMonth<=2){
                year=nowYear-1;
                month=12-2+nowMonth;
            }else{
                year=nowYear;
                month=nowMonth-2;
            }
        }else{
            if(nowMonth<=1){
                year=nowYear-1;
                month=12;
            }else{
                year=nowYear;
                month=nowMonth-1;
            }
        }

        System.out.println("year:"+year);

        System.out.println("month:"+month);

        System.out.println(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(Double.valueOf(131) - Double.valueOf(465), Double.valueOf(465))));

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        Date data=null;
        try {
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            data = sDateFormat.parse("2018-12-31 00:00:00");
        }catch (Exception e){
            e.printStackTrace();
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setMinimalDaysInFirstWeek(7);
        //cal.set(Calendar.YEAR, 2018);
        /*cal.set(Calendar.MONTH, 11);//11表示的是12月
        cal.set(Calendar.DATE, 31);*/
        cal.setTime(data);
        //53
        System.out.println("年："+cal.get(Calendar.YEAR));
        System.out.println("月："+cal.get(Calendar.MONTH));
        System.out.println("周数："+cal.get(Calendar.WEEK_OF_YEAR));
        System.out.println(new Date());
        System.out.println(new GregorianCalendar().getTime());
        System.out.println("周：："+Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));

        System.out.println("2018年周数："+getMaxWeekNumOfYear(2018));

        System.out.println(UUID.randomUUID().toString());

        //新建一个List 用的google提供的Guava  package com.google.common.collect;Lists.newArrayList();
        List<String> list11 =new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        list11.add("1");
        list11.add("2");
        list11.add("3");

        list2 = list11.stream().map(string -> {
            return "stream().map()处理之后：" + string;
        }).collect(Collectors.toList());

        list2.stream().forEach(string -> {
            System.out.println(string+",");
        });

        List<String> list1 = new ArrayList<String>();//新建一个List 用的google提供的Guava  package com.google.common.collect;
        list1.add("1");
        list1.add("2");
        list1.add("3");

        list1.stream().forEach(string ->{
            System.out.println(string);
        });

        List<Long> longList=new ArrayList<Long>();
        longList.add(11L);
        longList.add(2L);
        longList.add(3L);
        System.out.println(longList.toString());
        System.out.println(longList.contains(1L));

        String str11 = "abc1,bc";
        System.out.println(str11.contains("abc"));
        List<String> gameids = java.util.Arrays.asList(str11.split(","));
        System.out.println(gameids);

    }
    // 获取当前时间所在年的最大周数
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }
    // 获取当前时间所在年的周数
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }
}
