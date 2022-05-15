package com.marriage.model.marriage;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hu
 */
@Data
public class MarriageUserAdd {

    private Integer userId;
    /**
     * 真实名称
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 微信id
     */
    @NotBlank(message = "微信id不能为空")
    private String weChatId;

    /**
     * 籍贯
     */
    @NotBlank(message = "籍贯不能为空")
    private String nativePlace;

    /**
     * 目前所在地
     */
    @NotBlank(message = "所在地不能为空")
    private String currentLocation;

    /**
     * 0男性,1女性
     */
    @NotNull(message = "性别不能为空")
    private Integer gender;

    /**
     * 出生日期,根据出生日期计算属相和星座
     */
    @NotBlank(message = "生日不能为空")
    private String birthDate;

    /**
     * 0:A,1:B,2:O,3:AB,4:其他
     */
    @NotNull(message = "血型不能为空")
    private Integer bloodType;

    /**
     * 身高,cm
     */
    @NotNull(message = "身高不能为空")
    private Integer height;

    /**
     * 体重,kg
     */
    @NotNull(message = "体重不能为空")
    private Integer weight;

    /**
     *
     */
    @NotNull(message = "学历不能为空")
    private Integer education;

    @NotBlank(message = "毕业院校不能为空")
    private String graduationSchool;


    @NotNull(message = "资产不能为空")
    private Integer house;

    @NotBlank(message = "房车说明不能为空")
    private String houseInfo;

    @NotNull(message = "收入不能为空")
    private Integer income;

    /**
     * 工作介绍
     */
    @NotBlank(message = "工作不能为空")
    private String jobInfo;



    /**
     * 兴趣和性格介绍
     */
    @NotBlank(message = "兴趣不能为空")
    private String interest;

    /**
     *  0未婚;1离异无小孩,2离异有小孩
     */
    @NotNull(message = "婚姻状况不能为空")
    private Integer marriageStatus;

    /**
     * 家庭情况
     */
    @NotBlank(message = "家庭情况不能为空")
    private String family;

    /**
     * 对象要求
     */
    @NotBlank(message = "对象要求不能为空")
    private String target;

    /**
     * 爱情宣言
     */
    @NotBlank(message = "安全宣言不能为空")
    private String declaration;
    @NotNull(message = "照片不能为空")
    private List<String> imageUrlList;

    /**
     * 单身原因
     */
    @NotBlank(message = "单身原因不能为空")
    private String reasonsSingle;

    /**
     * 怎么了解我们的
     */
    @NotBlank(message = "怎么了解我们的不能为空")
    private String sourceFrom;

    /**
     * 备注
     */
    private String remark;

}

