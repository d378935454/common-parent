package com.springboot.socket.dao;

import com.springboot.socket.message.ClientInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by duhongda on 2017/5/14.
 */
public interface  ClientInfoRepository extends CrudRepository<ClientInfo, String> {
    ClientInfo findClientByclientid(String clientId);
}
