package com.study.bicycle.dao;

import com.study.bicycle.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class userDaoTest {
    @Autowired
    private UserDao dao;
    @Test
    public void findById(){
        List<User> list = dao.findAll();
        System.out.println(list.get(0).toString());
    }
}
