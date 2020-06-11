package com.study.bicycle.dao;

import com.study.bicycle.entity.Bicycle;
import com.study.bicycle.entity.RentRecord;
import com.study.bicycle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentRecordDao extends JpaRepository<RentRecord,Integer> {
        RentRecord findRentRecordByBicycleNoAndStatus(String bicycleNo, Integer status);
        List<RentRecord> findAllByRentUserAndStatus(User user,Integer status);


//        RentRecord  findByBicycleAndStatus(Bicycle bicycle,Integer status);

        List<RentRecord>  findByBicycleAndStatusAndRentUser(Bicycle bicycle,Integer status,User rentUser);
}
