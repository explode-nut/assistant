package com.czn.assistant.spider;

import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.exception.LegendsListInvalidException;
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
