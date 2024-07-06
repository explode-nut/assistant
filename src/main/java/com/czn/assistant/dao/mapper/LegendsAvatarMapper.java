package com.czn.assistant.dao.mapper;

import com.czn.assistant.dao.entity.LegendsAvatar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface LegendsAvatarMapper {
    void insert(@Param("avatar") LegendsAvatar legendsAvatar);

    void insertList(@Param("list") List<LegendsAvatar> list);

    LegendsAvatar get(@Param("name") String name);

    ArrayList<LegendsAvatar> getAll();

    void update(@Param("avatar") LegendsAvatar legendsAvatar);
}
