package com.marriage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author hu
 */
@AllArgsConstructor
@Getter
public enum User {
    /**
     * 用户类型
     */
    unregistered(0,"未注册","请提交信息注册"),
    unauthorized(1,"未授权","请联系管理员审批"),
    normal(2,"普通用户","欢迎登录"),
    admin(3,"管理员","欢迎管理员");

    
    private final int code;
    private final String value;
    private final String desc;


}
