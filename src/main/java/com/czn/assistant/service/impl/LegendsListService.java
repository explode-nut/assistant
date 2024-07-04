package com.czn.assistant.service.impl;

import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.dao.entity.LegendsAvatar;
import com.czn.assistant.dao.entity.LegendsList;
import com.czn.assistant.dao.mapper.LegendsAvatarMapper;
import com.czn.assistant.dao.mapper.LegendsListMapper;
import com.czn.assistant.dto.response.LegendsListDTO;
import com.czn.assistant.dto.response.LegendsListsDTO;
import com.czn.assistant.exception.LegendsListInvalidException;
import com.czn.assistant.service.ILegendsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<LegendsListDTO> getLegendsList() {
        ArrayList<LegendsList> legendsListArrayList = legendsListMapper.getAll();
        ArrayList<LegendsAvatar> legendsAvatarArrayList = legendsAvatarMapper.getAll();

        ArrayList<LegendsListDTO> result = new ArrayList<>();

        if (legendsListArrayList.size() != legendsAvatarArrayList.size()) {
            throw new LegendsListInvalidException(ResponseCodeEnum.Legends_List_INVALID_ERROR);
        } else {
            for (int i = 0; i < legendsListArrayList.size(); i++) {
                LegendsListDTO e = new LegendsListDTO();
                e.setId(legendsListArrayList.get(i).getId());
                e.setChineseName(legendsListArrayList.get(i).getChineseName());
                e.setName(legendsListArrayList.get(i).getName());
                e.setAvatarUrl(legendsAvatarArrayList.get(i).getUrl());
                result.add(e);
            }
            return result;
        }
    }

    @Override
    public LegendsListDTO getLegend(String name) {
        LegendsList legend = legendsListMapper.get(name);
        LegendsAvatar avatar = legendsAvatarMapper.get(name);

        LegendsListDTO result = new LegendsListDTO();
        result.setId(legend.getId());
        result.setChineseName(legend.getChineseName());
        result.setName(legend.getName());
        result.setAvatarUrl(avatar.getUrl());

        return result;
    }
}
