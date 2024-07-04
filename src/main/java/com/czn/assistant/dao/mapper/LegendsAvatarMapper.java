package com.czn.assistant.dao.mapper;

import com.czn.assistant.dao.entity.LegendsAvatar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface LegendsAvatarMapper {
    void insert(LegendsAvatar legendsAvatar);

    void insertList(@Param("list")ArrayList<LegendsAvatar> list);
}
