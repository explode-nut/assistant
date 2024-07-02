package com.czn.assistant.controller.admin;

import com.czn.assistant.util.Response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class TestController {
    @GetMapping("/test")
    public Response test() {
        log.info("这是日志");
        return Response.success("成功");
    }
}
