package com.marriage.utils;






import com.marriage.model.ResponseHead;
import com.marriage.model.ResponseMsg;
import com.marriage.model.ResponseStatus;

import java.util.UUID;

/**
 * @author hu
 */
public class ResponseMsgUtil {
    public static ResponseMsg<?> getSuccessMsg( String info, Object object){
        ResponseHead responseHead = new ResponseHead();
        responseHead.setRespDesc(info);
        responseHead.setRespCode(ResponseStatus.success.getCode());
        responseHead.setRespStatus(ResponseStatus.success.getStatus());
        responseHead.setRequestId(UUID.randomUUID().toString());
        ResponseMsg<Object> msg=new ResponseMsg<>();
        msg.setHead(responseHead);
        msg.setData(object);
        return msg;
    }

    public static  ResponseMsg<?> getFailMsg(ResponseStatus status,String info,Object object){
        ResponseMsg<Object> msg=new ResponseMsg<>();
        ResponseHead responseHead = new ResponseHead();
        responseHead.setRespDesc(info);
        responseHead.setRespCode(status.getCode());
        responseHead.setRespStatus(status.getStatus());
        responseHead.setRequestId(UUID.randomUUID().toString());
        msg.setHead(responseHead);
        msg.setData(object);
        return msg;
    }
}
