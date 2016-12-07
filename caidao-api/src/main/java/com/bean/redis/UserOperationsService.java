package com.bean.redis;

import com.bean.model.Customer;

public interface UserOperationsService {
    void add(Customer user);
     Customer getUser(String key);
 }