package com.study.bicycle.controller;

import com.study.bicycle.entity.Bicycle;
import com.study.bicycle.entity.RentRecord;
import com.study.bicycle.service.IRecordService;
import com.study.bicycle.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("record")
public class RentRecordController extends BaseController{

    @Autowired
    private IRecordService recordService;

    @GetMapping("usageRecord")
    public JsonResult<List<RentRecord>> usageRecord(Integer id){
        log.info("#########################");
        log.info("########正在使用的单车记录########");
        log.info("#########################");
        List<RentRecord> list = recordService.findUseRecord(id);
        return new JsonResult<>(list);
    }
    @GetMapping("recordList")
    public JsonResult<List<RentRecord>> recordList(Integer id){
        log.info("#########################");
        log.info("########已租赁的单车记录########");
        log.info("#########################");
        List<RentRecord> list = recordService.findAll(id);
        return new JsonResult<>(list);
    }

    @GetMapping("findByNoLikeRent")
    public JsonResult<List<RentRecord>> findByNoLikeRent(String bicycleNo,Integer userId){
        log.info("#########################");
        log.info("########模糊查询已租单车记录########");
        log.info("#########################");
        log.info("bicycleNo={}",bicycleNo);
        List<RentRecord> list = recordService.getRentRecordLikeRent(bicycleNo,userId);
        return new JsonResult<>(list);
    }

    @GetMapping("findByNoLikeNoRent")
    public JsonResult<List<RentRecord>> findByNoLikeNoRent(String bicycleNo,Integer userId){
        log.info("#########################");
        log.info("########模糊查询正在租用单车记录########");
        log.info("#########################");
        log.info("bicycleNo={}",bicycleNo);
        List<RentRecord> list = recordService.getRentRecordLikeNoRent(bicycleNo,userId);
        return new JsonResult<>(list);
    }
}
