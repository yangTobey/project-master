package com.spring.boot.handler;

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
import java.util.List;

/**
 * Created by Yang on 2018/12/12.
 * 注：https://blog.csdn.net/stonetudou/article/details/77850937
 */
@ControllerAdvice
class ExceptionhandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionhandlerController.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exception(HttpServletRequest request, Exception exception) {
        logger.error("raised exception : " + exception);
        return Response.error(exception.getMessage());
    }

    /**
     * 处理实体字段校验不通过异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response validationError(MethodArgumentNotValidException ex) {
        logger.error("raised MethodArgumentNotValidException : " + ex);
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage() + "\n");
        }
        return new Response(builder.toString(),"500");
    }
}
