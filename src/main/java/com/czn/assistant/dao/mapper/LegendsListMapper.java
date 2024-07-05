package com.czn.assistant.dao.mapper;

import com.czn.assistant.dao.entity.LegendsList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface LegendsListMapper {
    void insert(@Param("legend") LegendsList legend);

    void insertList(@Param("list") ArrayList<LegendsList> list);

    LegendsList get(@Param("name") String name);

    ArrayList<LegendsList> getAll();

    void update(@Param("legend") LegendsList legendsList);
}
