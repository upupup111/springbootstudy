package com.study.bicycle.service;

import com.study.bicycle.controller.ex.EmptyListException;
import com.study.bicycle.entity.Bicycle;
import com.study.bicycle.entity.RentRecord;
import com.study.bicycle.service.ex.BicycleUseException;
import com.study.bicycle.service.ex.PhoneNotFoundException;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.math.BigDecimal;
import java.util.List;

public interface IbicycleService {
    List<Bicycle> findAll();
    List<Bicycle> findByRent(Integer userId)throws PhoneNotFoundException;
    List<Bicycle> findByNoRent();
    void rentBicycle(Integer id, Integer userId)throws PhoneNotFoundException;
    void returnBicycle(Integer id,String money);
    void scrapBicycle(Integer id)throws BicycleUseException,PhoneNotFoundException;
    Boolean findByNo(String no);
    void addBicycle(Bicycle bicycle,Integer userId)throws PhoneNotFoundException;
    String getMoney(String bicycleNo);
    Integer createBicycleList(List<Bicycle> list)throws EmptyListException;

    List<Bicycle> getBicycleLike(String bicycleNo);

    List<Bicycle> getBicycleLikeRent(String bicycleNo);

    List<Bicycle> getBicycleLikeNoRent(String bicycleNo);
}
