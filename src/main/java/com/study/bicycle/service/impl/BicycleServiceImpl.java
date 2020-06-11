package com.study.bicycle.service.impl;

import com.study.bicycle.controller.BaseController;
import com.study.bicycle.controller.ex.EmptyListException;
import com.study.bicycle.dao.BicycleDao;
import com.study.bicycle.dao.RentRecordDao;
import com.study.bicycle.dao.UserDao;
import com.study.bicycle.entity.Bicycle;
import com.study.bicycle.entity.RentRecord;
import com.study.bicycle.entity.User;
import com.study.bicycle.service.IbicycleService;
import com.study.bicycle.service.ex.BicycleUseException;
import com.study.bicycle.service.ex.PhoneNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class BicycleServiceImpl implements IbicycleService {
    @Autowired
    private BicycleDao dao;
    @Autowired
    private RentRecordDao rentDao;
    @Autowired
    private UserDao userDao;
    public static Logger log = LoggerFactory.getLogger(BicycleServiceImpl.class);
    @Override
    public List<Bicycle> findAll() {
        return dao.findAllByIsDelete(Bicycle.NOT_DELETE);
    }

    @Override
    public List<Bicycle> findByRent(Integer userId)throws PhoneNotFoundException {
        User user = userDao.findUserById(userId);
        if(user==null){
            throw new PhoneNotFoundException("用户不存在");
        }
        return dao.findAllByIsRentAndRentUser(Bicycle.RENT,user);
    }

    @Override
    public List<Bicycle> findByNoRent() {
        return dao.findAllByIsRentAndIsDelete(Bicycle.NOT_RENT,Bicycle.NOT_DELETE);
    }

    @Override
    public void rentBicycle(Integer id, Integer userId) throws PhoneNotFoundException{
        Bicycle bicycle = dao.findBicycleById(id);
        User user = userDao.findUserById(userId);
        if(bicycle == null){
            throw new PhoneNotFoundException("未找到该单车");
        }
        bicycle.setIsRent(Bicycle.RENT);//已租赁
        bicycle.setRentUser(user);
        dao.save(bicycle);
        RentRecord rentRecord = new RentRecord();
        rentRecord.setRentUser(user);
        rentRecord.setBicycleNo(bicycle.getBicycleNo());
        rentRecord.setRentTime(new Timestamp(System.currentTimeMillis()));
        rentRecord.setStatus(RentRecord.BEING_USED);
        rentDao.save(rentRecord);
    }

    @Override
    public void returnBicycle(Integer id,String money) {
        Bicycle bicycle = dao.findBicycleById(id);
        if(bicycle == null){
            throw new PhoneNotFoundException("未找到该单车");
        }
        bicycle.setIsRent(Bicycle.NOT_RENT);
        bicycle.setRentUser(null);
        dao.save(bicycle);
        RentRecord rentRecord = rentDao.
                findRentRecordByBicycleNoAndStatus(bicycle.getBicycleNo(),RentRecord.BEING_USED);
        rentRecord.setStillTime(new Timestamp(System.currentTimeMillis()));
        rentRecord.setStatus(RentRecord.NOT_USED);
        rentRecord.setMoney(money);
        rentDao.save(rentRecord);
    }

    @Override
    public void scrapBicycle(Integer id) throws BicycleUseException,PhoneNotFoundException{
        Bicycle bicycle = dao.findBicycleById(id);
        if(bicycle==null){
            throw new PhoneNotFoundException("未找到单车");
        }
        if(bicycle.getIsRent()==Bicycle.RENT){
            throw new BicycleUseException("单车正在被租赁");
        }
        dao.deleteById(id);
    }

    @Override
    public Boolean findByNo(String no) {
        Boolean isExists = dao.existsByBicycleNo(no);
        return isExists;
    }

    @Override
    public void addBicycle(Bicycle bicycle,Integer userId)throws PhoneNotFoundException {
        if(bicycle==null){
            throw new PhoneNotFoundException("添加信息不存在");
        }
        bicycle.setIsRent(Bicycle.NOT_RENT);
        User user = userDao.findUserById(userId);
        bicycle.setAddUser(user);
        dao.save(bicycle);
    }

    @Override
    public String getMoney(String bicycleNo) {
        RentRecord rentRecord = rentDao.
                findRentRecordByBicycleNoAndStatus(bicycleNo,RentRecord.BEING_USED);
        NumberFormat currency = NumberFormat.getCurrencyInstance();//建立货币格式化引用
        long rentTime = rentRecord.getRentTime().getTime();
        long stillTime = System.currentTimeMillis();
        BigDecimal time = new BigDecimal(stillTime-rentTime);
        System.out.println(time);
        BigDecimal hours = new BigDecimal(1000*60*30);//半小时
        if(time.compareTo(hours)==1){
            BigDecimal[] num = time.divideAndRemainder(hours);
            if(num[1].compareTo(new BigDecimal(0))==0){//余数是否等于0
                return currency.format(new BigDecimal(2*num[0].intValue()));//金额为2元的倍数
            }else {
                return currency.format(new BigDecimal(2*(num[0].intValue()+1)));//金额为2元的(倍数加1)
            }
        }else {
            return currency.format(new BigDecimal(2));
        }
    }

    @Override
    public Integer createBicycleList(List<Bicycle> list) throws EmptyListException{
        if(list.size()==0) {
            throw new EmptyListException("导入的文件不能为空");
        }
        List<Bicycle> bicycles = list.stream().filter(item ->
            !dao.existsByBicycleNo(item.getBicycleNo())
        ).collect(Collectors.toList());//过滤已有的单车编号
        bicycles.stream().forEach(it -> it.setIsRent(Bicycle.NOT_RENT));
        if(bicycles.size()==0) {
            throw new EmptyListException("导入失败，导入的用户都已存在");
        }
        dao.saveAll(bicycles);
        log.info("导入"+bicycles.size()+"条数据成功");
        return bicycles.size();
    }

    @Override
    public List<Bicycle> getBicycleLike(String bicycleNo) {
        return dao.findByBicycleNoLike(bicycleNo);
    }

    @Override
    public List<Bicycle> getBicycleLikeRent(String bicycleNo) {
        return dao.findByBicycleNoaAndIsRent(bicycleNo,Bicycle.RENT);
    }

    @Override
    public List<Bicycle> getBicycleLikeNoRent(String bicycleNo) {
        return dao.findByBicycleNoaAndIsRent(bicycleNo,Bicycle.NOT_RENT);
    }
}
