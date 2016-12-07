package com.bean.service;

import com.bean.dao.VendingManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MyLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhao on 2016/6/30.
 */
@Service
public class VendingManagerServiceImpl implements VendingManagerService {
    private MyLogger LOGGER = new MyLogger(VendingManagerService.class);
    @Autowired
    private VendingManagerMapper vendingManagerMapper;

    public int validateManager(String managerCode, String passWord, String vendingCode) {
        Map<String, String> map = new HashMap<>();
        map.put("managerCode", managerCode);
        map.put("passWord", passWord);
        map.put("vendingCode", vendingCode);
        LOGGER.info("机器管理 validateManager" + "用户名：" + managerCode + ",密码：" + passWord + ",机器码：" + vendingCode);
        return vendingManagerMapper.validateManager(map);
    }

}
