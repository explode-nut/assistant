package com.czn.assistant.dao.mapper;

import com.czn.assistant.dao.entity.LegendsAvatar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class LegendsAvatarMapperTests {
    @Autowired
    LegendsAvatarMapper legendsAvatarMapper;

    @Test
    void testGet() {
        LegendsAvatar legendsAvatar = legendsAvatarMapper.get("Aatrox");
        LegendsAvatar expected = new LegendsAvatar();
        expected.setId(1);
        expected.setLegendsName("Aatrox");
        expected.setUrl("https://opgg-static.akamaized.net/meta/images/lol/14.13.1/champion/Aatrox.png?image=c_crop,h_103,w_103,x_9,y_9/q_auto:good,a_0,f_webp,w_160,h_160&v=1717557723274");
        Assertions.assertEquals(expected, legendsAvatar);
        System.out.println(legendsAvatar);
    }

    @Test
    void testGetAll() {
        ArrayList<LegendsAvatar> all = legendsAvatarMapper.getAll();
        System.out.println(all);
    }
}
