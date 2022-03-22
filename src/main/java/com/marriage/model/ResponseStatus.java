package com.marriage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hu
 */
@AllArgsConstructor
@Getter
public enum ResponseStatus {
    /**
     * 接口响应状态
     */
    success("00","请求成功"),
    phoneError("01","获取手机号码失败"),
    paramError("02","入参校验失败"),
    systemError("99","系统错误，请联系管理员");

    private final String code;
    private final String status;

}
