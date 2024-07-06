package com.czn.assistant.spider;

import com.czn.assistant.common.constant.OPGGSpiderConstant;
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
import java.util.List;

@Component
@Transactional(rollbackFor = Exception.class)
public class LegendsAvatarSpider implements BaseSpider{

    LegendsAvatarMapper legendsAvatarMapper;

    @Autowired
    public LegendsAvatarSpider(LegendsAvatarMapper legendsAvatarMapper) {
        this.legendsAvatarMapper = legendsAvatarMapper;
    }

    @Override
    public List doSpider() {
        ArrayList<LegendsAvatar> list = new ArrayList<>();
        try {
            Document document = Jsoup.connect(OPGGSpiderConstant.OPGG_INDEX_PAGE_URL_CONSTANT).get();
            Elements elements = document.selectXpath("//*[@id=\"content-container\"]/div[2]/aside/nav/ul/li");
            for (Element e : elements) {
                LegendsAvatar legendsAvatar = new LegendsAvatar();
                legendsAvatar.setLegendsName(e.text());
                legendsAvatar.setUrl(e.select("img").attr("src"));
                list.add(legendsAvatar);
            }
            return list;
        } catch (IOException e) {
            throw new LegendsAvatarInvalidException(ResponseCodeEnum.Legends_Avatar_INVALID_ERROR);
        }
    }

    @Override
    public void storeResult(List list) {
        legendsAvatarMapper.insertList(list);
    }
}
