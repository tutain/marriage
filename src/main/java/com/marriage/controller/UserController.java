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
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("checkUser")
    public ResponseMsg<?> checkUser(String phone){
        if(!StringUtils.hasLength(phone)){
            return getFailMsg(ResponseStatus.phoneError,"用户号码为空",null);
        }
        return getSuccessMsg("查询成功",userService.checkUser(phone).getCode());
    }

    @GetMapping("queryUser")
    public ResponseMsg<?> queryUser(MarriageQueryParam param){
        return getSuccessMsg("查询成功",userService.queryMarriages(param));
    }
    @GetMapping("queryDetail")
    public ResponseMsg<?> queryDetail(Integer id){
        if (id==null) {
            return getFailMsg(ResponseStatus.paramError, "id不能为空", null);
        }
        return getSuccessMsg("查询成功",userService.queryMarriageDetail(id));
    }

    @PostMapping("addUser")
    public ResponseMsg<?> addUser(@Valid MarriageUserAdd marriageUserAdd, BindingResult result){
        if (result.hasErrors()) {
            return getFailMsg(ResponseStatus.paramError, errorBinding(result), null);
        }
        return getSuccessMsg("新增成功",userService.addMarriage(marriageUserAdd));
    }
    @PutMapping("editUser")
    public ResponseMsg<?> editUser(@Valid MarriageUserEdit marriageUserEdit, BindingResult result){
        if (result.hasErrors()) {
            return getFailMsg(ResponseStatus.paramError, errorBinding(result), null);
        }
        return getSuccessMsg("修改成功",userService.editMarriage(marriageUserEdit));
    }
    @DeleteMapping("deleteUser")
    public ResponseMsg<?> editUser(Integer id){
        if (id==null) {
            return getFailMsg(ResponseStatus.paramError, "id不能为空", null);
        }
        return getSuccessMsg("删除成功",userService.deleteMarriage(id));
    }
    @PostMapping("uploadImage")
    public ResponseMsg<?> uploadImage(MultipartFile imageFile){
        if (imageFile==null) {
            return getFailMsg(ResponseStatus.paramError, "上传文件不能为空", null);
        }
        return getSuccessMsg("上传成功",userService.uploadFile(imageFile));
    }

    @PutMapping("changeUserStatus")
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

    private String errorBinding(BindingResult errorResult) {
        StringBuilder errorInfo = new StringBuilder();
        for (int i = 0; i < errorResult.getErrorCount(); i++) {
            errorInfo.append(errorResult.getAllErrors().get(i).getDefaultMessage());
        }
        return errorInfo.toString();
    }
}
