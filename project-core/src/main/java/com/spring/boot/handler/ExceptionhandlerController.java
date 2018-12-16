package com.spring.boot.handler;

import com.spring.boot.exception.auth.UserInvalidException;
import com.spring.boot.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Yang on 2018/12/12.
 * 异常处理类，用于在系统抛出异常时。向前端返回错误信息，达到友好提示的效果
 * 注：可自定义异常类,并且主动抛出异常，如：throw new UserInvalidException("用户不存在或账户密码错误!");
 * 注：https://blog.csdn.net/stonetudou/article/details/77850937
 */
@ControllerAdvice
@ResponseBody
class ExceptionhandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionhandlerController.class);

    @ExceptionHandler(value = Exception.class)
    public R exception(HttpServletRequest request, Exception exception) {
        logger.error("raised exception : " + exception);
        return R.error(400,exception.getMessage());
    }

    /**
     * 处理实体字段校验不通过异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R validationError(MethodArgumentNotValidException ex) {
        //logger.error("raised MethodArgumentNotValidException : " + ex);
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            System.out.println(error.getDefaultMessage());
            builder.append(error.getDefaultMessage() + "\n");
        }
        return R.error(400, builder.toString());
    }
    @ExceptionHandler(UserInvalidException.class)
    public R userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(),ex);
        return R.error(ex.getStatus(), ex.getMessage());
    }
}
