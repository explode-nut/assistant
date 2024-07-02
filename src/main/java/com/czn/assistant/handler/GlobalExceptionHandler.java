package com.czn.assistant.handler;

import com.czn.assistant.exception.LegendsListInvalidException;
import com.czn.assistant.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ LegendsListInvalidException.class })
    @ResponseBody
    public Response<Object> handleBizException(HttpServletRequest request, LegendsListInvalidException e) {
        return Response.fail(e.getErrorMessage());
    }
}
