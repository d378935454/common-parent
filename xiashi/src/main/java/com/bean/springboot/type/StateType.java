package com.bean.springboot.type;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ppctest02 on 2017/4/20.
 */
public enum StateType {
    CREATED("刚新建"),
    CHECKED("质检完"),
    SALE("夏实销售部"),
    TRANSPORT("物流"),
    REPOSITORY("仓库"),
    SYSTEM("供应链");
    private String desc;

    StateType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


    @JsonValue
    public Map<String, Object> returnMap() {
        Map<String, Object> map = new HashMap();
        map.put("name", this.name());
        map.put("value", this.ordinal());
        map.put("desc", this.desc);
        return map;
    }
}
