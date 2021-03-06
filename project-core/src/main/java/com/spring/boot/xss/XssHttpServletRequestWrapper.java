package com.spring.boot.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by Administrator on 2018/11/29.
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        super.getParameterMap();
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    /* 覆盖getParameter方法，将参数名和参数值都做xss过滤。
            * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
            * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
    */
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return value;
        //注：先阶段header没有自定义，如果自定义参数并且有传值操作数据库，请修改该处
        //return cleanXSS(value);
    }

    private String cleanXSS(String value) {

        //以请求头为实例：当value为:application/json, text/javascript, */*; q=0.01
        //注：replaceAll 的第一个参数是正则表达式，故而要经过两次转义，一次Java、一次正则。因此就需要四个反斜杠才可以匹配一个反斜杠。故而，替换一个反斜杠为空的replaceAll的代码即为：
        //str1 = str.replaceAll("\\\\","");
        //.*?是非贪婪的匹配，如果是贪婪的就是.*匹配任何字符，但用贪婪的就是不包含>的内容


        //You'll need to remove the spaces from the html entities below
        //value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        //value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("<script>(.*?)</script>", "");
        //value = value.replaceAll("</script>", "");
        value = value.replaceAll("expression\\((.*?)\\)", "");
        value = value.replaceAll("onload(.*?)=", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        //value = value.replaceAll("script", "");//当 text/javascript,会出现错误
        //value = value.replaceAll("[*]", "[" + "*]");//该处需要省略，假如请求头有*号会出现错误
        //value = value.replaceAll("[+]", "[" + "+]");
        //value = value.replaceAll("[?]", "[" + "?]");


        // replace sql 这里可以自由发挥
        String[] values = value.split(" ");

        /*String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|,|like|//|/|%|#";*/
        /*String badStr = "'|or|exec|execute|insert|select|delete|update|master|truncate|drop|javascript|count(*)|"
                + "declare|create|" + "grant|script|iframe" + "|--";*/

       /* String badStr = "'|or|exec|execute|insert|select|delete|master|truncate|drop|javascript|count(*)|"
                + "declare|create|" + "grant|script|iframe" + "|--";

        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[j].equalsIgnoreCase(badStrs[i])) {
                    values[j] = "forbid";
                }
            }
        }*/
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) {
                sb.append(values[i]);
            } else {
                sb.append(values[i] + " ");
            }
        }

        value = sb.toString();

        return value;
    }

}
