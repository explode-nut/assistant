package com.czn.assistant.spider;

import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.exception.LegendsListInvalidException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
public class LegendsListSpider implements BaseSpider {
    @Override
    public void doSpider() {
        throw new LegendsListInvalidException(ResponseCodeEnum.Legends_List_invalid_ERROR);
    }
}
