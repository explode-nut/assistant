package com.czn.assistant.common.enums;

import com.czn.assistant.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum  implements BaseExceptionInterface {
    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("500", "出错啦，后台小哥正在努力修复中..."),

    // ----------- 业务异常状态码 -----------
    Legends_List_invalid_ERROR("403", "英雄列表出错！"),
    ;

    // 异常码
    final private String errorCode;
    // 错误信息
    final private String errorMessage;
}
