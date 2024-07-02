package com.czn.assistant.spider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OPGGSpiderTests {

    @Autowired
    OPGGSpider opggSpider;
    @Test
    void testDoSpider() {
        opggSpider.doSpider();
    }
}
