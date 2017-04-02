package com.bean.springboot.utils;

import com.bean.springboot.dto.usermanagement.Menu;

import java.util.*;

/**
 * Created by bean on 2017/4/2.
 */
public class TreeUtil {
    public static Set<Menu> initMenus(Set<Menu> menus) {
        Set<Menu> tree = new HashSet<>();
        for (Menu menu : menus) {
            if (menu.getMenuOrder() == 0) {
                tree.add(menu);
                for (Menu menul : menus) {
                      if(menul.getMenuOrder() == 1&& Objects.equals(menul.getAdminId(), menu.getId())){
                          menu.getMenus().add(menul);
                      }
                }
            }
        }
        return tree;
    }
}
