package com.marriage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marriage.config.HttpsClientRequestFactory;
import com.marriage.dao.UserMapper;
import com.marriage.model.User;
import com.marriage.model.marriage.MarriageQueryParam;
import com.marriage.model.marriage.MarriageUser;
import com.marriage.model.marriage.MarriageUserAdd;
import com.marriage.model.marriage.MarriageUserEdit;
import com.marriage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hu
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Value("${appid}")
    private String appId;
    @Value("${secret}")
    private String secret;


    @Override
    public String queryWechatUserInfo(String code) {

        String url="https://api.weixin.qq.com/sns/jscode2session" +
                "?appid="+appId+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        ResponseEntity<String> response =new RestTemplate(new HttpsClientRequestFactory()).getForEntity(url, String.class);
        if(response.getStatusCode().is2xxSuccessful()){
            String user=response.getBody();
            if(user!=null&&!"".equals(user)){
                Map<String,Object> userMap= new HashMap<>();
                try {
                    userMap = new ObjectMapper().readValue(user, Map.class);
                    log.info(userMap+"");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return userMap.get("openid")+"";
            }
        }
        return "";
    }




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
    public void updateUserStatus(int id, int userStatus) {
        userMapper.updateUserStatus(userStatus,id);
    }
}
