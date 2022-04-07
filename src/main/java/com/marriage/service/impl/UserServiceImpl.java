package com.marriage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marriage.config.HttpsClientRequestFactory;
import com.marriage.dao.UserMapper;
import com.marriage.model.User;
import com.marriage.model.UserInfo;
import com.marriage.model.marriage.*;
import com.marriage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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
    public UserInfo checkUser(String weChatId) {
        Integer count=userMapper.selectAdmin(weChatId);
        UserInfo userInfo=new UserInfo();
        if(count!=null&&count>0){
            userInfo.setUser(User.admin);
            return userInfo;
        }
        MarriageUser user=userMapper.selectUserStatus(weChatId);
        if(user==null){
            userInfo.setUser(User.unregistered);
            return userInfo;
        }
        if(user.getUserStatus()==0){
            userInfo.setUser(User.unauthorized);
            userInfo.setUserId(user.getUserId());
            return userInfo;
        }
        if(user.getUserStatus()>0){
            userInfo.setUser(User.normal);
            userInfo.setUserId(user.getUserId());
            return userInfo;
        }
        userInfo.setUser(User.unregistered);
        return userInfo;

    }

    @Override
    public PageInfo<MarriageUser> queryMarriages(MarriageQueryParam param) {
        PageHelper.startPage(param.getPageNum(),param.getPageSize());
        List<MarriageUser> marriageUserList=userMapper.selectList(param);
        List<Integer> userIdList=new ArrayList<>();
        if(!CollectionUtils.isEmpty(marriageUserList)){
            marriageUserList.forEach(marriageUser -> userIdList.add(marriageUser.getUserId()));
            List<UserImage> images=userMapper.selectUserImage(userIdList);
            Map<Integer,List<String>> imageMap=images.stream().collect(Collectors.groupingBy(UserImage::getUserId,
                    Collectors.mapping(UserImage::getImagePath,Collectors.toList())));
            marriageUserList.forEach(marriageUser->marriageUser.setImageUrlList(imageMap.get(marriageUser.getUserId())));
        }

        return new PageInfo<>(marriageUserList);
    }

    @Override
    public MarriageUser queryMarriageDetail(int id) {
        MarriageUser user=userMapper.selectByPrimaryKey(id);
        List<UserImage> images=userMapper.selectUserImage(Collections.singletonList(id));
        user.setImageUrlList(images.stream().map(UserImage::getImagePath).collect(Collectors.toList()));
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addMarriage(MarriageUserAdd marriageUserAdd) {
         MarriageUser user=userMapper.selectByWeChatId(marriageUserAdd.getWeChatId());
         if(user!=null){
             return user.getUserId();
         }
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
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(int id, int userStatus) {
        userMapper.updateUserStatus(userStatus,id);
    }
}
