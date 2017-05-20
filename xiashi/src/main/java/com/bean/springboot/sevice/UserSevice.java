package com.bean.springboot.sevice;

import com.bean.springboot.dto.usermanagement.User;
import com.bean.springboot.type.PermissionType;

import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
public interface UserSevice {
   User getAllUser();

    /**
     * 通过用户名密码
     * @param account
     * @return
     */
    List<User> getUserByAcount(String account);

    /**
     * 根据角色类型得到用户
     * @param permissionType
     * @return
     */
    List<User> getUserByType(PermissionType permissionType);
}
