package com.bean.springboot.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum PermissionType {
    ADMIN("系统管理员"),
    SUPPLY("夏实供应部"),
    SALE("夏实销售部"),
    TRANSPORT("物流"),
    REPOSITORY("仓库"),
    SYSTEM("供应链");
    private String desc;

    PermissionType(String desc) {
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
