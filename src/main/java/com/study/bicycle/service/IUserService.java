package com.study.bicycle.service;

import com.study.bicycle.entity.User;
import com.study.bicycle.service.ex.ChangeInfoLoseException;
import com.study.bicycle.service.ex.PasswordError;
import com.study.bicycle.service.ex.PhoneIsHaveException;
import com.study.bicycle.service.ex.PhoneNotFoundException;

import java.util.List;

public interface IUserService {
    User login(String phone,String password)throws PhoneNotFoundException, PasswordError;
    User findUserInfo(Integer id)throws PhoneNotFoundException;
    void changeUserInfo(User user)throws ChangeInfoLoseException;
    List<User> findAll();
    void changeAdmin(Integer id)throws PhoneNotFoundException;
    User reg(String phone,String password)throws PhoneIsHaveException;

    List<User> findUserLike(String name);
}
