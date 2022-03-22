package com.marriage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marriage.dao.UserMapper;
import com.marriage.model.User;
import com.marriage.model.marriage.MarriageQueryParam;
import com.marriage.model.marriage.MarriageUser;
import com.marriage.model.marriage.MarriageUserAdd;
import com.marriage.model.marriage.MarriageUserEdit;
import com.marriage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User checkUser(String phone) {
        Integer count=userMapper.selectAdmin(phone);
        if(count!=null&&count>0){
            return User.admin;
        }
        Integer userStatus=userMapper.selectUserStatus(phone);
        if(userStatus==null){
            return User.unregistered;
        }
        if(userStatus==0){
            return User.unauthorized;
        }
        if(userStatus>0){
            return User.normal;
        }
        return User.unregistered;

    }

    @Override
    public PageInfo<MarriageUser> queryMarriages(MarriageQueryParam param) {
        PageHelper.startPage(param.getPageNum(),param.getPageSize());
        List<MarriageUser> marriageUserList=userMapper.selectList(param);
        return new PageInfo<>(marriageUserList);
    }

    @Override
    public MarriageUser queryMarriageDetail(int id) {
        MarriageUser user=userMapper.selectByPrimaryKey(id);
        //todo 实现图片展示
        List<String> images=userMapper.selectUserImage(id);
        user.setImageUrlList(images);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addMarriage(MarriageUserAdd marriageUserAdd) {
         userMapper.insert(marriageUserAdd);
         userMapper.insertUserImageBatch(marriageUserAdd.getImageUrlList(),marriageUserAdd.getUserId());
         return marriageUserAdd.getUserId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int editMarriage(MarriageUserEdit marriageUserEdit) {
        userMapper.updateByPrimaryKey(marriageUserEdit);
        userMapper.deleteUserImage(marriageUserEdit.getUserId());
        userMapper.insertUserImageBatch(marriageUserEdit.getImageUrlList(),marriageUserEdit.getUserId());
        return marriageUserEdit.getUserId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMarriage(int id) {
        userMapper.deleteByPrimaryKey(id);
        userMapper.deleteUserImage(id);
        return id;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        //todo 上传文件接口，微信小程序api
        return "";
    }

    @Override
    public void updateUserStatus(int id, int userStatus) {
        userMapper.updateUserStatus(userStatus,id);
    }
}
