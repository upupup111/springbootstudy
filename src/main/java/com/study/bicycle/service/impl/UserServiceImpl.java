package com.study.bicycle.service.impl;

import com.study.bicycle.dao.UserDao;
import com.study.bicycle.entity.User;
import com.study.bicycle.service.IUserService;
import com.study.bicycle.service.ex.ChangeInfoLoseException;
import com.study.bicycle.service.ex.PasswordError;
import com.study.bicycle.service.ex.PhoneIsHaveException;
import com.study.bicycle.service.ex.PhoneNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(String phone,String password)throws PhoneNotFoundException,PasswordError {
        User user =  userDao.findByPhone(phone);
        if(user!=null&&user.getPassword()!=null){
            if(user.getPassword().equals(password)){

                return user;
            }else {
                throw new PasswordError("密码错误");
            }
        }else{
            throw new PhoneNotFoundException("未找到用户");
        }

    }

    @Override
    public User findUserInfo(Integer id) throws PhoneNotFoundException{
        User user = userDao.findUserById(id);
        if(user==null){
            throw new PhoneNotFoundException("未找到用户信息");
        }
        return user;
    }

    @Override
    public void changeUserInfo(User user)throws ChangeInfoLoseException {
        User newUser = userDao.save(user);
        if(newUser==null){
            throw new ChangeInfoLoseException("修改信息失败");
        }
    }

    @Override
    public List<User> findAll() {
        return userDao.findAllByLevel(User.USER);
    }

    @Override
    public void changeAdmin(Integer id) throws PhoneNotFoundException{
        User user = userDao.findUserById(id);
        if(user==null){
            throw new PhoneNotFoundException("未找到用户信息");
        }
        user.setLevel(User.ADMIN);
        userDao.save(user);
    }

    @Override
    public User reg(String phone, String password) throws PhoneIsHaveException {
        boolean exists = userDao.existsUserByPhone(phone);
        if(exists){
            throw new PhoneIsHaveException("手机号已存在");
        }
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setLevel(User.USER);
        return userDao.save(user);
    }

    @Override
    public List<User> findUserLike(String name) {
        return userDao.findByNameAndLevel(name,User.USER);
    }
}
