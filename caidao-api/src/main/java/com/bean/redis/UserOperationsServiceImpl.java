package com.bean.redis;

import com.bean.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

//@Service
  public class UserOperationsServiceImpl implements UserOperationsService {
      @Autowired
      private RedisTemplate redisTemplate;

      @Override
     public void add(Customer user) {
          // TODO Auto-generated method stub
          /*
          * boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
         * public Boolean doInRedis(RedisConnection redisConnection) throws
          * DataAccessException { RedisSerializer<String> redisSerializer =
          * redisTemplate .getStringSerializer(); byte[] key =
          * redisSerializer.serialize(user.getId()); byte[] value =
          * redisSerializer.serialize(user.getName()); return
          * redisConnection.setNX(key, value); } }); return result;
          */
         ValueOperations<String, Customer> valueops = redisTemplate
                 .opsForValue();
         valueops.set(user.getCustomerId().toString(), user);
     }


     @Override
     public Customer getUser(String key) {
         ValueOperations<String, Customer> valueops = redisTemplate
                 .opsForValue();
      Customer user = valueops.get(key);
         return user;
     }

 }