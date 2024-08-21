package com.czn.assistant.service.impl;

import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.dao.entity.LegendsAvatar;
import com.czn.assistant.dao.entity.LegendsList;
import com.czn.assistant.dao.mapper.LegendsAvatarMapper;
import com.czn.assistant.dao.mapper.LegendsListMapper;
import com.czn.assistant.dto.response.LegendsListDTO;
import com.czn.assistant.exception.LegendsListInvalidException;
import com.czn.assistant.service.ILegendsListService;
import com.czn.assistant.spider.LegendsAvatarSpider;
import com.czn.assistant.spider.LegendsListSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LegendsListService implements ILegendsListService {

    LegendsListMapper legendsListMapper;
    LegendsAvatarMapper legendsAvatarMapper;
    LegendsListSpider legendsListSpider;
    LegendsAvatarSpider legendsAvatarSpider;

    @Autowired
    public LegendsListService(LegendsListMapper legendsListMapper, LegendsAvatarMapper legendsAvatarMapper, LegendsListSpider legendsListSpider, LegendsAvatarSpider legendsAvatarSpider) {
        this.legendsListMapper = legendsListMapper;
        this.legendsAvatarMapper = legendsAvatarMapper;
        this.legendsListSpider = legendsListSpider;
        this.legendsAvatarSpider = legendsAvatarSpider;
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

    @Override
    @Transactional
    public void updateLegend(LegendsListDTO legendsListDTO) {
        LegendsList legendsList = new LegendsList();
        LegendsAvatar legendsAvatar = new LegendsAvatar();
        BeanUtils.copyProperties(legendsListDTO, legendsList);
        legendsAvatar.setId(legendsListDTO.getId());
        legendsAvatar.setLegendsName(legendsListDTO.getName());
        legendsAvatar.setUrl(legendsListDTO.getAvatarUrl());

        legendsListMapper.update(legendsList);
        legendsAvatarMapper.update(legendsAvatar);
    }

    @Override
    @Transactional
    //刷新英雄列表，插入新英雄，每天执行一次这个方法
    //TODO 设置定时任务每天执行一次
    public void refreshList() {
        List<LegendsListDTO> legendsList = getLegendsList();
        List<LegendsList> legendsList1 = legendsListSpider.doSpider();
        List<LegendsAvatar> avatarList = legendsAvatarSpider.doSpider();
        Set<String> collect = legendsList.stream().map(LegendsListDTO::getName).collect(Collectors.toSet());
        //新爬取的列表中不包含的英雄名
        String name = "新英雄";
        if (legendsList1.size() != avatarList.size()) {
            throw new LegendsListInvalidException(ResponseCodeEnum.Legends_List_INVALID_ERROR);
        } else {
            for (LegendsList list : legendsList1) {
                name = list.getName();
                if (!collect.contains(name)) {
                    //插入新英雄
                    LegendsList legend = new LegendsList();
                    legend.setName(name);
                    legend.setChineseName("暂无");
                    LegendsAvatar avatar = new LegendsAvatar();
                    avatar.setLegendsName(name);
                    for (LegendsAvatar e : avatarList) {
                        if (name.equals(e.getLegendsName())) {
                            avatar.setUrl(e.getUrl());
                        }
                    }
                    legendsListMapper.insert(legend);
                    legendsAvatarMapper.insert(avatar);
                    log.info("新插入一个英雄:" + name);
                }
            }
        }
    }

}
