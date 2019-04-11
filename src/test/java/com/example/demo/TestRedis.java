package com.example.demo;



import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.mysql.cj.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Test
    public  void redisTest(){
        stringRedisTemplate.opsForValue().set("redis","111");
        String value=stringRedisTemplate.opsForValue().get("redis");
        System.out.println(value+"--------------------");

    }


    @Test
   public void testObj() throws InterruptedException {
        User user=userMapper.findByName("sun");
        ValueOperations<String,Object> operations=redisTemplate.opsForValue();
        operations.set("a23",user);
        operations.set("sun,s",user, 1,TimeUnit.SECONDS);
        Thread.sleep(1000);

        boolean exit=redisTemplate.hasKey("a23");
        if (exit){
            System.out.println("he is exit--------");
        }else {
            System.out.println("sorry he is not exie");
        }

   }


    @Test
    @Cacheable(value = "user-key")
   public  void TestCach(){
        User user=userMapper.findByName("java");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");

        System.out.println();
   }

}
