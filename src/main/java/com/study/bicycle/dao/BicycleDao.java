package com.study.bicycle.dao;

import com.study.bicycle.entity.Bicycle;
import com.study.bicycle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BicycleDao extends JpaRepository<Bicycle,Integer> {
    List<Bicycle> findAllByIsDelete(Integer isDelete);

    Bicycle findBicycleById(Integer id);

    List<Bicycle> findAllByIsRentAndIsDelete(Integer isRent,Integer IsDelete);

    List<Bicycle> findAllByIsRentAndRentUser(Integer isRent, User user);

    Boolean existsByBicycleNo(String no);

    Bicycle findBicycleByBicycleNo(String bicycleNo);

    List<Bicycle>  findByBicycleNoLike(String bicycleNo);

    @Query(value = "select b from Bicycle b where b.bicycleNo like %:bicycleNo% and b.isRent = :isRent ")
    List<Bicycle>  findByBicycleNoaAndIsRent(@Param("bicycleNo") String bicycleNo, @Param("isRent") Integer isRent);
}
