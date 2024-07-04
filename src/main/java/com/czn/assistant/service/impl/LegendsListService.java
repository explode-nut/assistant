package com.czn.assistant.service.impl;

import com.czn.assistant.dao.entity.LegendsAvatar;
import com.czn.assistant.dao.entity.LegendsList;
import com.czn.assistant.dao.mapper.LegendsAvatarMapper;
import com.czn.assistant.dao.mapper.LegendsListMapper;
import com.czn.assistant.dto.response.LegendsListDTO;
import com.czn.assistant.dto.response.LegendsListsDTO;
import com.czn.assistant.service.ILegendsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LegendsListService implements ILegendsListService {

    LegendsListMapper legendsListMapper;
    LegendsAvatarMapper legendsAvatarMapper;

    @Autowired
    public LegendsListService(LegendsListMapper legendsListMapper, LegendsAvatarMapper legendsAvatarMapper) {
        this.legendsListMapper = legendsListMapper;
        this.legendsAvatarMapper = legendsAvatarMapper;
    }

    @Override
    public LegendsListsDTO getLegendsList() {
        ArrayList<LegendsList> legendsListArrayList = legendsListMapper.getAll();
        ArrayList<LegendsAvatar> legendsAvatarArrayList = legendsAvatarMapper.getAll();

        ArrayList<LegendsListDTO> legendsListDTOS = new ArrayList<>();
        LegendsListsDTO result = new LegendsListsDTO();

        if (legendsListArrayList.size() != legendsAvatarArrayList.size()) {
            return null;
        } else {
            for (int i = 0; i < legendsListArrayList.size(); i++) {
                LegendsListDTO e = new LegendsListDTO();
                e.setId(legendsListArrayList.get(i).getId());
                e.setChineseName(legendsListArrayList.get(i).getChineseName());
                e.setName(legendsListArrayList.get(i).getName());
                e.setAvatarUrl(legendsAvatarArrayList.get(i).getUrl());
                legendsListDTOS.add(e);
            }
            result.setList(legendsListDTOS);
            return result;
        }
    }
}
