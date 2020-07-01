package com.scaffold.common.advice;

import com.scaffold.common.constant.Enum.Separator;
import com.scaffold.common.exception.*;
import com.scaffold.common.util.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * @Author danyiran
 * @create 2020/7/1 23:23
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    public ExceptionHandlerAdvice() {
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundException handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.error("throw NoHandlerFoundException:", ex);
        String msg = MessageHelper.getMessage("MSG999994", new Object[]{ex.getRequestURL()});
        return new ResourceNotFoundException(msg);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public MethodNotSupportedException handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("throw HttpRequestMethodNotSupportedException:", ex);
        String msg = MessageHelper.getMessage("MSG999993", new Object[]{ex.getMethod()});
        return new MethodNotSupportedException(msg);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ParamInvalidException handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuffer sb = new StringBuffer();
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            Iterator i$ = errorList.iterator();

            while (i$.hasNext()) {
                ObjectError error = (ObjectError) i$.next();
                sb.append(error.getDefaultMessage()).append(Separator.LINE.val());
            }
        }

        return new ParamInvalidException(sb.toString());
    }

    @ExceptionHandler({ServletRequestBindingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ParamInvalidException handleServletRequestBindingException(ServletRequestBindingException ex) {
        log.error("throw ServletRequestBindingException:", ex);
        String msg = MessageHelper.getMessage("MSG999995", new Object[]{ex.getMessage()});
        return new ParamInvalidException(msg);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RootException handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("throw HttpMessageNotReadableException:", ex);
        String msg = MessageHelper.getMessage("MSG999997");
        return new ParamInvalidException(msg);
    }


    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundException handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ex;
    }

    @ExceptionHandler({ParamInvalidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RootException handleBadRequestException(RootException ex) {
        return ex;
    }

    @ExceptionHandler({ServerException.class, RootException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RootException handleInternalServerErrorException(RootException ex) {
        return ex;
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.OK)
    public RootException handleBusinessException(RootException ex) {
        return ex;
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RootException handleSQLException(Exception ex) {
        log.error("throw SQLException:", ex);
        String msg = MessageHelper.getMessage("MSG999998");
        return new ServerException(msg);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RootException handleSystemException(Exception ex) {
        log.error("throw Exception:", ex);
        String msg = MessageHelper.getMessage("MSG999999");
        return new ServerException(msg);
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public RootException handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.error("throw HttpMediaTypeNotSupportedException:", ex);
        String msg = MessageHelper.getMessage("MSG999990", ex.getContentType());
        return new RootException(msg, "SC_415");
    }

    @ExceptionHandler({AuthException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RootException handleHttpNotAuthException(AuthException ex) {
        log.error("throw HttpMediaTypeNotSupportedException:", ex);
        String msg = MessageHelper.getMessage("MSG999989");
        return new RootException(msg, "SC_401");
    }
}
