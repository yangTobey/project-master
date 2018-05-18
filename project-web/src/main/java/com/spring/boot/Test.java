package com.spring.boot;

import com.spring.boot.util.UtilHelper;
import org.apache.shiro.crypto.hash.Hash;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

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

    }
}
