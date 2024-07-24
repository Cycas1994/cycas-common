package com.cycas.common.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xin.na
 * @since 2024/7/18 17:23
 */
@Getter
@AllArgsConstructor
public enum CommonResponseEnum {

    ERROR(-1, "失败"),
    SUCCESS(200, "成功"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "无权限"),
    METHOD_NOT_ALLOWED(405, "方法不允许"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    BIZ_ERROR(1000, "业务异常"),
    SYSTEM_IS_BUSY(1001, "系统繁忙,请稍后再试!"),
    PARAMETER_ERROR(1002, "参数错误"),
    DATA_ERROR(1003, "数据异常"),
    IP_ERROR(1004, "IP获取失败"),
            ;

    private int code;
    private String message;

}
