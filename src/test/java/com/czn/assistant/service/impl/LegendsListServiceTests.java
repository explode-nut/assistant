package com.czn.assistant.service.impl;

import com.czn.assistant.dto.response.LegendsListsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LegendsListServiceTests {
    @Autowired
    LegendsListService legendsListService;

    @Test
    void testGetLegendsList() {
        LegendsListsDTO legendsList = legendsListService.getLegendsList();
        System.out.println(legendsList);
    }
}
