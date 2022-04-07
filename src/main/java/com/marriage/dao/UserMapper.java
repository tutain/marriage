package com.marriage.dao;

import com.marriage.model.marriage.*;
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
     * @param weChatId
     * @return
     */
    Integer selectAdmin(String weChatId);

    /**
     * 查询用户状态
     *
     * @param weChatId
     * @return
     */
    MarriageUser selectUserStatus(String weChatId);

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
     * @param userIdList
     * @return
     */
    List<UserImage> selectUserImage(List<Integer> userIdList);

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
     *
     * @param userId
     * @return
     */
    int deleteUserImage(int userId);

    /**
     * 根据wechatId查询用户
     * @param weChatId
     * @return
     */
    MarriageUser selectByWeChatId(String weChatId);
}
