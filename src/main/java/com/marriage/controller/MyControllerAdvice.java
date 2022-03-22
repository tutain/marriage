package com.marriage.controller;



import com.marriage.model.ResponseHead;
import com.marriage.model.ResponseMsg;
import com.marriage.model.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author hu
 */
@ControllerAdvice
public class MyControllerAdvice {
    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseMsg<?> errorHandler(Exception ex) {
        final ResponseMsg<Object> response = new ResponseMsg<>();
        final ResponseHead rspHead = new ResponseHead();
        rspHead.setRespStatus(ResponseStatus.systemError.getStatus());
        rspHead.setRespCode(ResponseStatus.systemError.getCode());
        rspHead.setRespDesc(ex.getMessage());
        response.setHead(rspHead);
        return response;
    }

    /**
     * 拦截捕捉运行时异常 RuntimeException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseMsg<?> myErrorHandler(RuntimeException ex) {
        final ResponseMsg<Object> response = new ResponseMsg<>();
        final ResponseHead rspHead = new ResponseHead();
        rspHead.setRespStatus(ResponseStatus.systemError.getStatus());
        rspHead.setRespCode(ResponseStatus.systemError.getCode());
        rspHead.setRespDesc("请求处理失败：" + ex.getMessage());
        response.setHead(rspHead);
        return response;
    }

}
