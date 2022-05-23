package com.marriage.controller;

import com.marriage.model.ResponseMsg;
import com.marriage.model.ResponseStatus;
import com.marriage.model.marriage.MarriageQueryParam;
import com.marriage.model.marriage.MarriageUserAdd;
import com.marriage.model.marriage.MarriageUserEdit;
import com.marriage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.marriage.utils.ResponseMsgUtil.getFailMsg;
import static com.marriage.utils.ResponseMsgUtil.getSuccessMsg;

/**
 * @author hu
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("queryWechatUserInfo")
    @ResponseBody
    public ResponseMsg<?> queryWechatUserInfo(String code){
        return getSuccessMsg("查询成功",userService.queryWechatUserInfo(code));
    }
    @GetMapping("checkUser")
    @ResponseBody
    public ResponseMsg<?> checkUser(String weChatId){
        if(!StringUtils.hasLength(weChatId)){
            return getFailMsg(ResponseStatus.weChatIdError,"用户id为空",null);
        }
        return getSuccessMsg("查询成功",userService.checkUser(weChatId));
    }

    @GetMapping("queryUser")
    @ResponseBody
    public ResponseMsg<?> queryUser(MarriageQueryParam param){
        return getSuccessMsg("查询成功",userService.queryMarriages(param));
    }
    @GetMapping("queryDetail")
    @ResponseBody
    public ResponseMsg<?> queryDetail(Integer id){
        if (id==null) {
            return getFailMsg(ResponseStatus.paramError, "id不能为空", null);
        }
        return getSuccessMsg("查询成功",userService.queryMarriageDetail(id));
    }

    @PostMapping("addUser")
    @ResponseBody
    public ResponseMsg<?> addUser(@Valid MarriageUserAdd marriageUserAdd, BindingResult result){
        if (result.hasErrors()) {
            return getFailMsg(ResponseStatus.paramError, errorBinding(result), null);
        }
        return getSuccessMsg("新增成功",userService.addMarriage(marriageUserAdd));
    }
    @PutMapping("editUser")
    @ResponseBody
    public ResponseMsg<?> editUser(@Valid MarriageUserEdit marriageUserEdit, BindingResult result){
        if (result.hasErrors()) {
            return getFailMsg(ResponseStatus.paramError, errorBinding(result), null);
        }
        return getSuccessMsg("修改成功",userService.editMarriage(marriageUserEdit));
    }
    @DeleteMapping("deleteUser")
    @ResponseBody
    public ResponseMsg<?> editUser(Integer id){
        if (id==null) {
            return getFailMsg(ResponseStatus.paramError, "id不能为空", null);
        }
        return getSuccessMsg("删除成功",userService.deleteMarriage(id));
    }


    @PutMapping("changeUserStatus")
    @ResponseBody
    public ResponseMsg<?> uploadImage(Integer id,Integer userStatus){
        if (id==null) {
            return getFailMsg(ResponseStatus.paramError, "用户id不能为空", null);
        }
        if (userStatus==null) {
            return getFailMsg(ResponseStatus.paramError, "用户状态不能为空", null);
        }
        userService.updateUserStatus(id,userStatus);
        return getSuccessMsg("修改成功",null);
    }

    @GetMapping("countNewUser")
    @ResponseBody
    public ResponseMsg<?> countNewUser(){
        return getSuccessMsg("查询成功",userService.countNewUser());
    }

    private String errorBinding(BindingResult errorResult) {
        StringBuilder errorInfo = new StringBuilder();
        for (int i = 0; i < errorResult.getErrorCount(); i++) {
            errorInfo.append(errorResult.getAllErrors().get(i).getDefaultMessage());
        }
        return errorInfo.toString();
    }
}
