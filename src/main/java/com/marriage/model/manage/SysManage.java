package com.marriage.model.manage;

import lombok.Data;

/**
 * @author hu
 */
@Data
public class SysManage {
    /**
     * pk
     */
    private int manageId;

    /**
     * 管理员名称
     */
    private String manageName;

    /**
     * 管理员微信id认证用
     */
    private String manageWxId;

    /**
     * 0有效,1无效
     */
    private int isValid;
}

