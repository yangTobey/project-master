package com.spring.boot;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/3/19.
 */
public class Test {
    public static void main(String[] args) {
        float num= (float)2/3;
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数
        String s = df.format(num);//返回的是String类型
        String s1 = df.format(0.555541);//返回的是String类型
        String s2 = df.format((float)20/21);//返回的是String类型
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(Double.valueOf(s2)*100);
        System.out.println(Double.valueOf(s2));
        BigDecimal bigd = new BigDecimal((float)20/21);
        System.out.println(bigd);
        System.out.println(bigd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
    }
}
