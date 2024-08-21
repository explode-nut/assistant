package com.czn.assistant.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LegendsPositionMap {
    private final Map<String, Integer> positionMap;

    public LegendsPositionMap() {
        positionMap = new HashMap<>();
        this.positionMap.put("Top", 1);
        this.positionMap.put("Jungle", 2);
        this.positionMap.put("Middle", 3);
        this.positionMap.put("Bottom", 4);
        this.positionMap.put("Support", 5);
    }

    //输入位置名获取位置对应int值
    public Integer getPosition(String position) {
        return positionMap.get(position);
    }
}
