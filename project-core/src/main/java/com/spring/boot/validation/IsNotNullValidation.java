package com.spring.boot.validation;

import com.spring.boot.util.UtilHelper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/1/2.
 */
public class IsNotNullValidation implements ConstraintValidator<IsNotNull, Object> {
    /**
     * 表示金额的正则表达式
     */
    private String moneyReg = "^\\d+(\\.\\d{1,2})?$";
    private Pattern moneyPattern = Pattern.compile(moneyReg);

    @Override
    public void initialize(IsNotNull isNotNull) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext arg1) {
        //return moneyPattern.matcher(value.toString()).matches();
        if (value==null) {
            return false;
        }else{
            //拦截不符合常理数字填入，捕捉类型转换错误（因无法保证用户输入的数据完全符合常理，需要增加该操作）
            try{
                if(value instanceof Double){
                    if((Double)value>=Integer.MAX_VALUE){
                        return false;
                    }
                    //double类型只保留两位小数
                    Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
                    //double数字过大时，会以科学计数法显示，该处需要避免科学计数法
                    BigDecimal bigDecimal = new BigDecimal(value.toString());
                    //System.out.println("数据："+bigDecimal.toString());
                    Matcher isNum = pattern.matcher(bigDecimal.toString());
                    //System.out.println("数据格式："+isNum.matches());
                    return isNum.matches();
                }else if (value instanceof String) {
                    return StringUtils.isNotEmpty(value.toString());
                } else if (value instanceof Collection) {
                    return !org.springframework.util.CollectionUtils.isEmpty((Collection) value);
                } else if (value instanceof Map) {
                    return MapUtils.isNotEmpty((Map) value);
                } else if (value.getClass().isArray()) {
                    return Array.getLength(value) > 0;
                }
                return true;
              //https://blog.csdn.net/mr_rain/article/details/78247857
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
}
