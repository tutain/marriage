CREATE TABLE `marriage_user`
(
    `user_id`           int           NOT NULL AUTO_INCREMENT COMMENT 'pk',
    `user_name`         varchar(20)   NOT NULL COMMENT '真实名称',
    `wechat_id`         varchar(100)  NOT NULL COMMENT '微信id',
    `native_place`      varchar(100)  NOT NULL COMMENT '籍贯',
    `current_location`  varchar(100)  NOT NULL COMMENT '目前所在地',
    `gender`            tinyint       NOT NULL COMMENT '0男性,1女性',
    `birth_date`        timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '出生日期,根据出生日期计算属相和星座',
    `blood_type`        tinyint       NOT NULL COMMENT '0:A,1:B,2:O,3:AB,4:其他',
    `height`            int           NOT NULL COMMENT '身高,cm',
    `weight`            int           NOT NULL COMMENT '体重,kg',
    `education`         tinyint       NOT NULL COMMENT '0大专,1本科,2研究生,3博士,4其他',
    `graduation_school` varchar(10)   NOT NULL DEFAULT '' COMMENT '毕业学校',
    `house`             tinyint       NOT NULL COMMENT '0无房,1有房,2多套房',
    `house_info`        varchar(2000) NOT NULL DEFAULT '' COMMENT '房车介绍',
    `job_info`          varchar(2000) NOT NULL COMMENT '工作介绍',
    `income`            int           NOT NULL DEFAULT '0' COMMENT '年收入,单位万',
    `interest`          varchar(2000) NOT NULL COMMENT '兴趣和性格介绍',
    `marriage_status`   tinyint       NOT NULL COMMENT '0未婚;1离异无小孩,2离异有小孩',
    `family`            varchar(2000) NOT NULL COMMENT '家庭情况',
    `target`            varchar(2000) NOT NULL COMMENT '对象要求',
    `user_status`       tinyint       NOT NULL DEFAULT '0' COMMENT '0已经注册,1公开2屏蔽',
    `declaration`       varchar(2000) NOT NULL COMMENT '爱情宣言',
    `reasons_single`    varchar(2000) NOT NULL DEFAULT '' COMMENT '单身原因',
    `source_from`       varchar(2000) NOT NULL DEFAULT '' COMMENT '怎么了解我们的',
    `remark`            varchar(2000)          DEFAULT NULL COMMENT '其他说明',
    `register_date`     timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
    `update_date`       timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `idx_marriage_user_wxid` (`wechat_id`) USING BTREE
) COMMENT ='交友注册用户';



CREATE TABLE `sys_manage`
(
    `manage_id`    int          NOT NULL AUTO_INCREMENT COMMENT 'pk',
    `manage_name`  varchar(100) NOT NULL COMMENT '管理员名称',
    `manage_wx_id` varchar(100) NOT NULL COMMENT '管理员证用',
    `is_valid`     tinyint      NOT NULL COMMENT '0有效,1无效',
    PRIMARY KEY (`manage_id`)
) COMMENT ='系统管理员';

CREATE TABLE `user_image`
(
    `image_id`   int           NOT NULL AUTO_INCREMENT COMMENT 'pk',
    `user_id`    int           NOT NULL COMMENT '用户id',
    `image_path` varchar(2000) NOT NULL COMMENT '照片路径或者fileid',
    PRIMARY KEY (`image_id`)
) COMMENT ='用户照片表';



