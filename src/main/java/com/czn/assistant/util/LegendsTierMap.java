package com.czn.assistant.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LegendsTierMap {
    private final Map<String, Integer> tierMap;

    public LegendsTierMap() {
        tierMap = new HashMap<>();
        this.tierMap.put("emerald_plus", 1);
        this.tierMap.put("diamond_plus", 2);
        this.tierMap.put("master_plus", 3);
        this.tierMap.put("grandmaster", 4);
        this.tierMap.put("challenger", 5);
    }

    //输入段位名获取位置对应int值
    public Integer getTier(String position) {
        return tierMap.get(position);
    }
}
