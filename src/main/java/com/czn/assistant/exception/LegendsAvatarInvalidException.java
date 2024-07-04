package com.czn.assistant.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegendsAvatarInvalidException extends RuntimeException{
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public LegendsAvatarInvalidException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
