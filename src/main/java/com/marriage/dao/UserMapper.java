package com.marriage.dao;

import com.marriage.model.marriage.MarriageQueryParam;
import com.marriage.model.marriage.MarriageUser;
import com.marriage.model.marriage.MarriageUserAdd;
import com.marriage.model.marriage.MarriageUserEdit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hu
 */
@Mapper
public interface UserMapper {

    /**
     * 查询时候为管理员
     *
     * @param phone
     * @return
     */
    Integer selectAdmin(String phone);

    /**
     * 查询用户状态
     *
     * @param phone
     * @return
     */
    Integer selectUserStatus(String phone);

    /**
     * 根据id删除用户
     *
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(int userId);

    /**
     * 新增用户
     *
     * @param record
     * @return
     */
    int insert(MarriageUserAdd record);

    /**
     * 查询单个用户详情
     *
     * @param userId
     * @return
     */

    MarriageUser selectByPrimaryKey(int userId);

    /**
     * 更新用户信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(MarriageUserEdit record);

    /**
     * 根据条件筛选
     *
     * @param param
     * @return
     */
    List<MarriageUser> selectList(MarriageQueryParam param);

    /**
     * 更新用户状态
     *
     * @param status
     * @param id
     * @return
     */
    int updateUserStatus(@Param("userStatus") int status, @Param("userId") int id);

    /**
     * 根据用户id查询上传的照片
     *
     * @param userId
     * @return
     */
    List<String> selectUserImage(int userId);

    /**
     * 批量插入图片
     *
     * @param imageList
     * @param id
     * @return
     */
    int insertUserImageBatch(@Param("imageList") List<String> imageList, @Param("userId") int id);

    /**
     * 根据用户id删除图片
     * @param userId
     * @return
     */
    int deleteUserImage(int userId);
}