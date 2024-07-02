package com.czn.assistant.spider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LegendsListSpiderTests {
    @Autowired
    LegendsListSpider legendsListSpider;

    @Test
    void testDoSpider() {
        legendsListSpider.doSpider();
    }
}
