package com.czn.assistant.spider;

import java.util.List;

public interface BaseSpider {
    List doSpider();

    void storeResult(List list);
}
