package com.cycas.common.biz.handler;

import com.cycas.common.biz.enums.CommonResponseEnum;
import com.cycas.common.biz.exception.BizException;
import com.cycas.common.biz.model.vo.RInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author xin.na
 * @since 2024/7/18 17:18
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public RInfoVO BizExceptionHandler(BizException e) {
        log.error("业务异常:", e);
        return RInfoVO.build(e.getCode(), e.getMessage(), null);
    }

    /**
     * 数据异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = DataAccessException.class)
    @ResponseBody
    public RInfoVO DataAccessExceptionHandler(DataAccessException e) {
        log.error("数据异常:", e);
        return RInfoVO.build(CommonResponseEnum.DATA_ERROR.getCode(), CommonResponseEnum.DATA_ERROR.getMessage(), null);
    }

    /**
     * 参数验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public RInfoVO MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.warn("参数验证异常:", e);
        return RInfoVO.build(CommonResponseEnum.PARAMETER_ERROR.getCode(), e.getBindingResult().getFieldError().getDefaultMessage(), null);
    }

    /**
     * 未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RInfoVO exceptionHandler(Exception e) {
        log.warn("未知异常:", e);
        return RInfoVO.build(CommonResponseEnum.INTERNAL_SERVER_ERROR.getCode(), CommonResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public RInfoVO httpMediaTypeNotSupportedExceptionHandler(Exception e) {
        return RInfoVO.build(CommonResponseEnum.PARAMETER_ERROR.getCode(), CommonResponseEnum.PARAMETER_ERROR.getMessage(), null);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "MethodNotSupport")
    @ResponseBody
    public RInfoVO httpRequestMethodNotSupportedException(Exception e) {
        return RInfoVO.build(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
    }
}
