package com.study.bicycle.service;

import com.study.bicycle.entity.RentRecord;
import com.study.bicycle.service.ex.PhoneNotFoundException;

import java.util.List;

public interface IRecordService {
    List<RentRecord> findUseRecord(Integer id)throws PhoneNotFoundException;
    List<RentRecord> findAll(Integer id)throws PhoneNotFoundException;


    List<RentRecord> getRentRecordLikeRent(String bicycleNo,Integer userId);

    List<RentRecord> getRentRecordLikeNoRent(String bicycleNo,Integer userId);
}
