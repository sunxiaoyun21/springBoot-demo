package com.example.demo.util;


import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * 添加cach的配置类
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{


    @Bean
    public KeyGenerator keyGenerator() {
       return new KeyGenerator(){

           @Override
           public Object generate(Object o, Method method, Object... objects) {
               StringBuilder sb = new StringBuilder();
               sb.append(o.getClass().getName());
               sb.append(method.getName());
               for (Object obj : objects) {
                   sb.append(obj.toString());
               }
               return sb.toString();
           }
       };
    }
}
