package com.spring.boot;

import com.spring.boot.util.UtilHelper;
import org.apache.shiro.crypto.hash.Hash;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    }
}
