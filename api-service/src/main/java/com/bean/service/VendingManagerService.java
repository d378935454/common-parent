package com.bean.service;

/**
 * Created by zhao on 2016/6/30.
 */
public interface VendingManagerService {
    /**
     * 验证机器管理员
     *
     * @param managerCode 管理员用户名
     * @param passWord    管理员密码
     * @param vendingCode 机器码
     * @return
     */
    int validateManager(String managerCode, String passWord, String vendingCode);
}
