package com.spring.boot.aspect;

/**
 * Created by Yang on 2018/11/24.
 */
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {

    String msg() default "字段不能为空";

}
