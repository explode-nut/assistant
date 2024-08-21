package com.czn.assistant.handler;

import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.exception.LegendsAvatarInvalidException;
import com.czn.assistant.exception.LegendsListInvalidException;
import com.czn.assistant.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ LegendsListInvalidException.class })
    @ResponseBody
    public Response<Object> handleLegendsListInvalidException(WebRequest request, LegendsListInvalidException e) {
        return Response.fail(ResponseCodeEnum.Legends_List_INVALID_ERROR.getErrorMessage(), ResponseCodeEnum.Legends_List_INVALID_ERROR.getErrorCode());
    }

    @ExceptionHandler({ LegendsAvatarInvalidException.class })
    @ResponseBody
    public Response<Object> handleLegendsAvatarInvalidException(WebRequest request, LegendsAvatarInvalidException e) {
        return Response.fail(ResponseCodeEnum.Legends_Avatar_INVALID_ERROR.getErrorMessage(), ResponseCodeEnum.Legends_Avatar_INVALID_ERROR.getErrorCode());
    }

    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public Response<Object> handleCommonException(WebRequest request, Exception e) {
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR.getErrorMessage(), ResponseCodeEnum.SYSTEM_ERROR.getErrorCode());
    }
}
