package com.study.bicycle.util;

import com.study.bicycle.controller.BaseController;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class JsonResult<T> {
    private static Logger log = LoggerFactory.getLogger(JsonResult.class);
    private Integer code;
    private String message;
    private T data;
    public JsonResult(){
        this.code = BaseController.SUCCESS;
    }
    public JsonResult(Integer code) {
        this.code = code;
    }

    public JsonResult(Integer code,T data) {
        this.code = code;
        this.data = data;
        log.info("返回信息为={}",this.data);
    }

    public JsonResult(T data) {
        this.code= BaseController.SUCCESS;
        this.data = data;
        log.info("返回信息为={}",this.data);
    }
    public JsonResult(Throwable e){
        this.message = e.getMessage();
        log.error("错误提示={}",this.message);
    }
}
