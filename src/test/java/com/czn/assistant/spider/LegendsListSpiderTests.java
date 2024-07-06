package com.czn.assistant.spider;

import com.czn.assistant.dao.entity.LegendsList;
import com.czn.assistant.dao.mapper.LegendsListMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LegendsListSpiderTests {
    @Autowired
    LegendsListSpider legendsListSpider;

    @Autowired
    LegendsListMapper legendsListMapper;

    @Test
    void testDoSpider() {
        List list = legendsListSpider.doSpider();
        System.out.println(list);
    }

    @Test
    void testStoreResult() {
        List<LegendsList> list = legendsListSpider.doSpider();
        legendsListSpider.storeResult(list);
        ArrayList<LegendsList> all = legendsListMapper.getAll();
        System.out.println(all);
    }
}
