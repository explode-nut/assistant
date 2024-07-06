package com.czn.assistant.spider;

import com.czn.assistant.common.constant.OPGGSpiderConstant;
import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.dao.entity.LegendsList;
import com.czn.assistant.dao.mapper.LegendsListMapper;
import com.czn.assistant.exception.LegendsListInvalidException;
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
public class LegendsListSpider implements BaseSpider {

    LegendsListMapper legendsListMapper;

    @Autowired
    public LegendsListSpider(LegendsListMapper legendsListMapper) {
        this.legendsListMapper = legendsListMapper;
    }

    @Override
    public List doSpider() {
        ArrayList<LegendsList> list = new ArrayList<>();
        try {
            Document document = Jsoup.connect(OPGGSpiderConstant.OPGG_INDEX_PAGE_URL_CONSTANT).get();
            Elements elements = document.selectXpath("//*[@id=\"content-container\"]/div[2]/aside/nav/ul/li");
            for (Element e : elements) {
                LegendsList legend = new LegendsList();
                legend.setName(e.text());
                legend.setChineseName("暂无");
                list.add(legend);
            }
            return list;
        } catch (IOException e) {
            throw new LegendsListInvalidException(ResponseCodeEnum.Legends_List_INVALID_ERROR);
        }
    }

    @Override
    public void storeResult(List list) {
        legendsListMapper.insertList(list);
    }
}
