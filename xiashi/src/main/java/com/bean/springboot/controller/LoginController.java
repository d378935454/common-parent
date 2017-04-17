package com.bean.springboot.controller;

import com.bean.springboot.dto.usermanagement.User;
import com.bean.springboot.sevice.UserSevice;
import com.bean.springboot.utils.MD5Util;
import com.bean.springboot.utils.RSTFulBody;
import com.bean.springboot.utils.TreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by bean on 2017/4/3.
 */
@RequestMapping("mobile")
@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MD5Util md5Util;
    @Autowired
    private UserSevice userSevice;

    /**
     * @throws Exception
     * @discription 用户登录
     * AJAX提交
     */
    @RequestMapping("check-login")
    public RSTFulBody ldapsubmit(HttpServletRequest request,User user) throws Exception {
        String account = user.getAccountName();
        String pw = user.getPassword();
        if (account == null || pw == null) {
            return new RSTFulBody().fail().msg("用户民和密码不可为空");
        }
        List<User> users = userSevice.getUserByAcount(account);
        if (users == null || users.isEmpty()) {
            return new RSTFulBody().fail().msg("账号不存在");
        } else if (!md5Util.getMD5ofStr(pw).equals(users.get(0).getPassword())) {
            return new RSTFulBody().fail().msg("密码错误");
        }
        log.info(">>>>>>姓名>>>>>>>" + account);
        log.info(">>>>>>密码>>>>>>>" + pw);

        users.get(0).setMenus(TreeUtil.initMenus(users.get(0).getPermission().getMenus()));
        return new RSTFulBody().success(users.get(0));
    }
}

