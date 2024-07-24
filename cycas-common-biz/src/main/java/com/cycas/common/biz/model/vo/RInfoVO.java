package com.cycas.common.biz.model.vo;

import com.cycas.common.biz.enums.CommonResponseEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("返回对象")
public class RInfoVO<T> {

    @ApiModelProperty(value = "响应业务状态")
    private int code;
    @ApiModelProperty(value = "响应消息")
    private String message;
    @ApiModelProperty(value = "响应中的数据")
    private T data;

    public RInfoVO() {
    }

    public static <T> RInfoVO<T> build(int code, String message, T data) {
        return new RInfoVO<>(code, message, data);
    }

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public boolean isSuccess() {
        return CommonResponseEnum.SUCCESS.getCode() == code;
    }

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

    // 成功
    public static <T> RInfoVO<T> ok(T data) {
        return new RInfoVO<>(data);
    }

    public static <T> RInfoVO<T> ok() {
        return new RInfoVO<>(null);
    }

    // 错误
    public static <T> RInfoVO<T> error(String message) {
        return new RInfoVO<>(CommonResponseEnum.ERROR.getCode(), message, null);
    }

    public static <T> RInfoVO<T> error(String message, T data) {
        return new RInfoVO<>(CommonResponseEnum.ERROR.getCode(), message, data);
    }

    public static <T> RInfoVO<T> error(T data) {
        return new RInfoVO<>(CommonResponseEnum.ERROR.getCode(), CommonResponseEnum.ERROR.getMessage(), data);
    }

    public RInfoVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RInfoVO(T data) {
        this.code = CommonResponseEnum.SUCCESS.getCode();
        this.message = CommonResponseEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RInfoVO{" +
                "code=" + code +
                ", message='" + message +
                ", data=" + data +
                '}';
    }

}
