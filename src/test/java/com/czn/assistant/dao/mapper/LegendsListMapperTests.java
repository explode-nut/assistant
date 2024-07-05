package com.czn.assistant.dao.mapper;

import com.czn.assistant.dao.entity.LegendsList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class LegendsListMapperTests {

    @Autowired
    LegendsListMapper legendsListMapper;

    @Test
    void testGet() {
        LegendsList aatrox = legendsListMapper.get("Aatrox");
        LegendsList expected = new LegendsList();
        expected.setId(1);
        expected.setName("Aatrox");
        expected.setChineseName("暂无");
        Assertions.assertEquals(expected, aatrox);
        System.out.println(aatrox);
    }

    @Test
    void testGetAll() {
        ArrayList<LegendsList> all = legendsListMapper.getAll();
        System.out.println(all);
    }

    @Test
    void testUpdate() {
        LegendsList expected = new LegendsList();
        expected.setId(1);
        expected.setChineseName("暗裔剑魔");
        expected.setName("Aatrox");
        legendsListMapper.update(expected);
        LegendsList legendsList = legendsListMapper.get("Aatrox");
        Assertions.assertEquals(expected, legendsList);
        System.out.println(legendsList);
    }
}
