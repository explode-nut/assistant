package com.czn.assistant.spider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LegendsAvatarSpiderTests {

    @Autowired
    LegendsAvatarSpider legendsAvatarSpider;

    @Test
    void testDoSpider() {
        legendsAvatarSpider.doSpider();
    }
}
