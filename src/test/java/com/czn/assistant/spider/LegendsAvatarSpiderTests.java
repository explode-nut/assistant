package com.czn.assistant.spider;

import com.czn.assistant.dao.entity.LegendsAvatar;
import com.czn.assistant.dao.mapper.LegendsAvatarMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LegendsAvatarSpiderTests {

    @Autowired
    LegendsAvatarSpider legendsAvatarSpider;

    @Autowired
    LegendsAvatarMapper legendsAvatarMapper;

    @Test
    void testDoSpider() {
        List list = legendsAvatarSpider.doSpider();
        System.out.println(list);
    }

    @Test
    void testStoreResult() {
        List list = legendsAvatarSpider.doSpider();
        legendsAvatarSpider.storeResult(list);
        ArrayList<LegendsAvatar> all = legendsAvatarMapper.getAll();
        System.out.println(all);
    }
}
