package com.study.bicycle.dao;


import com.study.bicycle.entity.User;
import org.omg.CORBA.INTERNAL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings("unchecked")
public interface UserDao extends JpaRepository<User,Integer> {
    User findByPhone(String phone);
    User findUserById(Integer id);
    List<User> findAllByLevel(Integer level);
    Boolean existsUserByPhone(String phone);
    @Query(value = "select u from User u where u.name like %:name% and u.level = :level ")
    List<User> findByNameAndLevel(@Param("name")String name,@Param("level") Integer level);
}
