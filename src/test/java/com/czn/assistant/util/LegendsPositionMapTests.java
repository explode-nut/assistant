package com.czn.assistant.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LegendsPositionMapTests {

    @Autowired
    LegendsPositionMap legendsPositionMap;

    @Test
    void testGetPosition() {
        Integer position = legendsPositionMap.getPosition("Top");
        Assertions.assertEquals(1, position);
        System.out.println(position);
    }
}
