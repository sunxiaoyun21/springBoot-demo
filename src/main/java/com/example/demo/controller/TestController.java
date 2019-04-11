package com.example.demo.controller;


import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class TestController {

   @Autowired
   private UserMapper userMapper;

   @RequestMapping(value = "/hello",method = RequestMethod.GET)
   public String sayHello(){
       return "hello springBoot";
   }



   @RequestMapping("/uid")
   public String uid(HttpSession session){
       UUID uuid= (UUID) session.getAttribute("uid");
       if(uuid==null){
           uuid=UUID.randomUUID();
       }
       session.setAttribute("uid",uuid);
       return session.getId();

   }

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user=userMapper.findByName("java");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }
}
