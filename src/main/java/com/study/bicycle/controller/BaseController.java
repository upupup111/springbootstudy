package com.study.bicycle.controller;

import com.study.bicycle.controller.ex.ControllerException;
import com.study.bicycle.controller.ex.EmptyListException;
import com.study.bicycle.controller.ex.FileOutException;
import com.study.bicycle.service.ex.*;
import com.study.bicycle.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
public class BaseController {
    public static final Integer SUCCESS = 200;
    protected static Logger log = LoggerFactory.getLogger(BaseController.class);
    @ExceptionHandler({ServiceException.class, ControllerException.class})
    @ResponseBody
    public JsonResult<Void> HandleException(Throwable e){
        log.error("发生的错误:"+e.getMessage());
        JsonResult<Void> jr =new JsonResult<>(e);
        if(e instanceof PhoneNotFoundException){
            jr.setCode(401);
        }else if(e instanceof PasswordError){
            jr.setCode(402);
        }else if(e instanceof ChangeInfoLoseException){
            jr.setCode(403);
        }else if(e instanceof PhoneIsHaveException){
            jr.setCode(405);
        }else if(e instanceof BicycleUseException){
            jr.setCode(406);
        }else if(e instanceof EmptyListException){
            jr.setCode(501);
        }else if(e instanceof FileOutException){
            jr.setCode(502);
        }
        return jr;
    }
}
