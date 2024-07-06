package com.czn.assistant.service;

import com.czn.assistant.dto.response.LegendsListDTO;

import java.util.List;

public interface ILegendsListService {

    List<LegendsListDTO> getLegendsList();

    LegendsListDTO getLegend(String name);

    void updateLegend(LegendsListDTO legendsListDTO);

    void refreshList();
}
