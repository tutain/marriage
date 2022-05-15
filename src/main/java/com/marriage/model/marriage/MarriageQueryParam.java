package com.marriage.model.marriage;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hu
 */
@Data
public class MarriageQueryParam {
    /**
     * pk
     */
    private Integer userId;
    /**
     * 微信id
     */
    private String weChatId;

    private Integer gender;

    /**
     * 出生日期,根据出生日期计算属相和星座
     */
    private String birthDateStart;
    private String birthDateEnd;
    /**
     * 身高,cm
     */
    private Integer heightStart;
    private Integer heightEnd;

    /**
     * 0大专,1本科,2研究生,3博士,4其他
     */
    private Integer education;

    /**
     * 0无房,1有房,2多套房
     */
    private Integer house;

    private int incomeStart;
    private int incomeEnd;

    private int pageNum;
    private int pageSize;

    /**
     * 0普通用户，1管理员
     */
    private int admin;


}

