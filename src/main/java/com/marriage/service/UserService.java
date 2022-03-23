package com.marriage.service;

import com.github.pagehelper.PageInfo;
import com.marriage.model.User;
import com.marriage.model.marriage.MarriageQueryParam;
import com.marriage.model.marriage.MarriageUser;
import com.marriage.model.marriage.MarriageUserAdd;
import com.marriage.model.marriage.MarriageUserEdit;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hu
 */
public interface UserService {
    /**
     * 查询用户openid
     * @param code
     * @return
     */
    String queryWechatUserInfo(String code);
    /**
     * 判断用户类型
     * @param phone
     * @return
     */
    User checkUser(String phone);

    /**
     * 分页查询用户信息
     * @param param
     * @return
     */
    PageInfo<MarriageUser> queryMarriages(MarriageQueryParam param);

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    MarriageUser queryMarriageDetail(int id);

    /**
     * 新增用户
     * @param marriageUserAdd
     * @return
     */
    int addMarriage(MarriageUserAdd marriageUserAdd);

    /**
     * 修改用户信息
     * @param marriageUserEdit
     * @return
     */
    int editMarriage(MarriageUserEdit marriageUserEdit);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteMarriage(int id);



    /**
     * 变更用户状态
     * @param id
     * @param userStatus
     */
    void updateUserStatus(int id,int userStatus);
}
