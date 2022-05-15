package com.marriage.model.marriage;

import lombok.Data;

import java.util.List;

/**
 * @author hu
 */
@Data
public class MarriageUser {
    /**
     * pk
     */
    private int userId;

    /**
     * 真实名称
     */
    private String userName;


    /**
     * 微信id
     */
    private String weChatId;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 目前所在地
     */
    private String currentLocation;

    /**
     * 0男性,1女性
     */
    private int gender;

    /**
     * 出生日期,根据出生日期计算属相和星座
     */
    private String birthDate;

    /**
     * 0:A,1:B,2:O,3:AB,4:其他
     */
    private int bloodType;

    /**
     * 身高,cm
     */
    private int height;

    /**
     * 体重,kg
     */
    private int weight;

    /**
     * 0大专,1本科,2研究生,3博士,4其他
     */
    private int education;

    private String graduationSchool;

    /**
     * 0无房,1有房,2多套房
     */
    private int house;

    private String houseInfo;

    private int income;

    /**
     * 工作介绍
     */
    private String jobInfo;

    /**
     * 兴趣和性格介绍
     */
    private String interest;

    /**
     * 0未婚;1离异无小孩,2离异有小孩
     */
    private int marriageStatus;

    /**
     * 家庭情况
     */
    private String family;

    /**
     * 对象要求
     */
    private String target;

    /**
     * 爱情宣言
     */
    private String declaration;

    /**
     * 单身原因
     */
    private String reasonsSingle;

    /**
     * 怎么了解我们的
     */
    private String sourceFrom;

    /**
     * 备注
     */
    private String remark;

    private List<String> imageUrlList;

    /**
     * 0待审批，1正常用户,2已介绍或脱单,3隐藏
     */
    private int userStatus;
}

