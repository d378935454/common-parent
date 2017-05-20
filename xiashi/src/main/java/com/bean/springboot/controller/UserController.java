package com.bean.springboot.controller;

import com.bean.springboot.dto.usermanagement.User;
import com.bean.springboot.sevice.UserSevice;
import com.bean.springboot.type.PermissionType;
import com.bean.springboot.utils.RSTFulBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ppctest02 on 2017/4/20.
 */
@RestController
@RequestMapping("mobile/user")
public class UserController extends CommenController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserSevice userSevice;

    @RequestMapping("getUserByType")
    public RSTFulBody getUserByType(PermissionType permissionType){
        List<User>  users=userSevice.getUserByType(permissionType);
        return new RSTFulBody().data(users);
    }

}
