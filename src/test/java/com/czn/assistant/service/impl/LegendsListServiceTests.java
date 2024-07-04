package com.czn.assistant.service.impl;

import com.czn.assistant.dto.response.LegendsListDTO;
import com.czn.assistant.dto.response.LegendsListsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LegendsListServiceTests {
    @Autowired
    LegendsListService legendsListService;

    @Test
    void testGetLegendsList() {
        List<LegendsListDTO> legendsList = legendsListService.getLegendsList();
        System.out.println(legendsList);
    }

    @Test
    void testGetLegend() {
        LegendsListDTO aatrox = legendsListService.getLegend("Aatrox");
        LegendsListDTO expected = new LegendsListDTO();
        expected.setId(1);
        expected.setChineseName("暂无");
        expected.setName("Aatrox");
        expected.setAvatarUrl("https://opgg-static.akamaized.net/meta/images/lol/14.13.1/champion/Aatrox.png?image=c_crop,h_103,w_103,x_9,y_9/q_auto:good,a_0,f_webp,w_160,h_160&v=1717557723274");
        Assertions.assertEquals(expected, aatrox);
        System.out.println(aatrox);
    }
}
