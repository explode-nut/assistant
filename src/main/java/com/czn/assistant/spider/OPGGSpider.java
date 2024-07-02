package com.czn.assistant.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OPGGSpider implements BaseSpider {

    LegendsListSpider legendsListSpider;

    @Autowired
    public OPGGSpider(LegendsListSpider legendsListSpider) {
        this.legendsListSpider = legendsListSpider;
    }

    @Override
    public void doSpider() {
        legendsListSpider.doSpider();
    }
}
