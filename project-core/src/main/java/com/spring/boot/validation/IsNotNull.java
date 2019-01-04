package com.spring.boot.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2019/1/2.
 * https://www.cnblogs.com/aut-lory/p/9233337.html
 * https://www.cnblogs.com/albert1024/articles/8436270.html
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=IsNotNullValidation.class)

public @interface IsNotNull {
    String message() default "{org.validation.constraints.IsNotNull.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
