package com.cycas.common.biz.exception;

import com.cycas.common.biz.enums.CommonResponseEnum;
import com.cycas.common.biz.interfaces.ICode;
import lombok.Data;

@Data
public class BizException extends RuntimeException {

    private Integer code;
    private String message;

    public BizException() {
    }

    public BizException(String message) {
        this.code = CommonResponseEnum.BIZ_ERROR.getCode();
        this.message = message;
    }

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(CommonResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BizException(ICode iCode) {
        this.code = iCode.getCode();
        this.message = iCode.getMessage();
    }

    public BizException(ICode exceptionCode, String... strings) {
        this.code = exceptionCode.getCode();
        this.message = String.format(exceptionCode.getMessage(), strings);
    }

}
