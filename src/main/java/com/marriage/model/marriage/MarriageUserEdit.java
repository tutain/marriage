package com.marriage.model.marriage;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hu
 */
@Data
public class MarriageUserEdit {

    @NotNull(message = "用户id为空")
    private Integer userId;
    /**
     * 真实名称
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;
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
     * 0大专及以下,1本科,2研究生,3博士,4博士后,5教授以上
     */
    @NotNull(message = "学历不能为空")
    private Integer education;

    /**
     * 0无房，有购房资格;1有房，在还贷款;2有房无贷;3多套房
     */
    @NotNull(message = "资产不能为空")
    private Integer house;

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
     * 0未婚;1已婚离异
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
    @NotBlank(message = "照片不能为空")
    private List<String> imageUrlList;

}

