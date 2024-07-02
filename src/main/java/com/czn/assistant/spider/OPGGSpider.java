package com.czn.assistant.spider;

import org.springframework.stereotype.Component;

@Component
public class OPGGSpider implements BaseSpider{
    @Override
    public void doSpider() {
        System.out.println("success");
    }
}
