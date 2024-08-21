package com.czn.assistant.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LegendsTierMapTests {
    @Autowired
    LegendsTierMap legendsTierMap;

    @Test
    void testGetTier() {
        Integer tier = legendsTierMap.getTier("diamond_plus");
        Assertions.assertEquals(2, tier);
        System.out.println(tier);
    }
}
