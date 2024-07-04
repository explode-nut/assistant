package com.czn.assistant.spider;

import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.dao.entity.LegendsAvatar;
import com.czn.assistant.dao.mapper.LegendsAvatarMapper;
import com.czn.assistant.exception.LegendsAvatarInvalidException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;

@Component
@Transactional(rollbackFor = Exception.class)
public class LegendsAvatarSpider implements BaseSpider{

    LegendsAvatarMapper legendsAvatarMapper;

    @Autowired
    public LegendsAvatarSpider(LegendsAvatarMapper legendsAvatarMapper) {
        this.legendsAvatarMapper = legendsAvatarMapper;
    }

    @Override
    public void doSpider() {
        ArrayList<LegendsAvatar> list = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://www.op.gg/champions").get();
            Elements elements = document.selectXpath("//*[@id=\"content-container\"]/div[2]/aside/nav/ul/li");
            for (Element e : elements) {
                LegendsAvatar legendsAvatar = new LegendsAvatar();
                legendsAvatar.setLegendsName(e.text());
                legendsAvatar.setUrl(e.select("img").attr("src"));
                list.add(legendsAvatar);
            }
            legendsAvatarMapper.insertList(list);
        } catch (IOException e) {
            throw new LegendsAvatarInvalidException(ResponseCodeEnum.Legends_Avatar_INVALID_ERROR);
        }
    }
}