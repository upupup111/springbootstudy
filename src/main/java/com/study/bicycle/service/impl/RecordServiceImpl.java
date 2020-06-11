package com.study.bicycle.service.impl;

import com.study.bicycle.dao.BicycleDao;
import com.study.bicycle.dao.RentRecordDao;
import com.study.bicycle.dao.UserDao;
import com.study.bicycle.entity.Bicycle;
import com.study.bicycle.entity.RentRecord;
import com.study.bicycle.entity.User;
import com.study.bicycle.service.IRecordService;
import com.study.bicycle.service.ex.PhoneNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements IRecordService {
    private static Logger log = LoggerFactory.getLogger(RecordServiceImpl.class);
    @Autowired
    private RentRecordDao dao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BicycleDao bicycleDao;
    @Override
    public List<RentRecord> findUseRecord(Integer id)throws PhoneNotFoundException {
        User user = userDao.findUserById(id);
        if(user==null){
            throw new PhoneNotFoundException("未找到用户");
        }
        List<RentRecord> list = dao.findAllByRentUserAndStatus(user,RentRecord.BEING_USED);
        return list;
    }

    @Override
    public List<RentRecord> findAll(Integer id) throws PhoneNotFoundException{
        User user = userDao.findUserById(id);
        if(user==null){
            throw new PhoneNotFoundException("未找到用户");
        }
        List<RentRecord> list = dao.findAllByRentUserAndStatus(user,RentRecord.NOT_USED);
        return list;
    }

    @Override
    public List<RentRecord> getRentRecordLikeRent(String bicycleNo,Integer userId) {
        log.info("以租单车");
        List<Bicycle> list = bicycleDao.findByBicycleNoaAndIsRent(bicycleNo,Bicycle.NOT_RENT);
        User user = userDao.findUserById(userId);
        if(user==null){
            throw new PhoneNotFoundException("未找到用户");
        }
        List<RentRecord> recordList = new ArrayList<>();
        for(Bicycle bicycle:list){
            List<RentRecord> rentRecord = dao.findByBicycleAndStatusAndRentUser(bicycle,RentRecord.NOT_USED,user);
            recordList.addAll(rentRecord);
        }
        return recordList;
    }

    @Override
    public List<RentRecord> getRentRecordLikeNoRent(String bicycleNo,Integer userId) {
        log.info("正在租赁");
        List<Bicycle> list = bicycleDao.findByBicycleNoaAndIsRent(bicycleNo,Bicycle.RENT);
        User user = userDao.findUserById(userId);
        if(user==null){
            throw new PhoneNotFoundException("未找到用户");
        }
        List<RentRecord> recordList = new ArrayList<>();
        for(Bicycle bicycle:list){
            List<RentRecord> rentRecord = dao.findByBicycleAndStatusAndRentUser(bicycle,RentRecord.BEING_USED,user);
            recordList.addAll(rentRecord);
        }
        return recordList;
    }
}
