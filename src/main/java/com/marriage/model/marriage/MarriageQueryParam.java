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
     * 真实名称
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机
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
    private Integer gender;

    /**
     * 出生日期,根据出生日期计算属相和星座
     */
    private String birthDateStart;
    private String birthDateEnd;
    /**
     * 0:A,1:B,2:O,3:AB,4:其他
     */
    private Integer bloodType;

    /**
     * 身高,cm
     */
    private Integer heightStart;
    private Integer heightEnd;
    /**
     * 体重,kg
     */
    private Integer weightStart;
    private Integer weightEnd;
    /**
     * 0大专及以下,1本科,2研究生,3博士,4博士后,5教授以上
     */
    private Integer education;

    /**
     * 0无房，有购房资格;1有房，在还贷款;2有房无贷;3多套房
     */
    private Integer house;

    /**
     * 工作介绍
     */
    private String jobInfo;

    /**
     * 兴趣和性格介绍
     */
    private String interest;

    /**
     * 0未婚;1已婚离异
     */
    private Integer marriageStatus;

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

    private int pageNum;
    private int pageSize;


}

