package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;



    @Test
    @Rollback
    public void findByName() {
       User user= userMapper.findByName("sun");
        System.out.println(user.getPassword()+"-------"+user.getScore());
    }

}
