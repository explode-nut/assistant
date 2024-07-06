package com.czn.assistant.controller.admin;

import com.czn.assistant.dto.response.LegendsListDTO;
import com.czn.assistant.service.impl.LegendsListService;
import com.czn.assistant.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
public class LegendsListController {

    LegendsListService legendsListService;

    @Autowired
    public LegendsListController(LegendsListService legendsListService) {
        this.legendsListService = legendsListService;
    }

    @GetMapping("/get/{name}")
    public Response<LegendsListDTO> get(@PathVariable("name") String name) {
        LegendsListDTO result = legendsListService.getLegend(name);
        log.info("获取英雄:" + name);

        return Response.success(result);
    }

    @GetMapping("/getLegendsList")
    public Response<List<LegendsListDTO>> getLegendsList() {
        List<LegendsListDTO> legendsList = legendsListService.getLegendsList();
        log.info("获取英雄列表");

        return Response.success(legendsList);
    }

    @PutMapping("/update")
    public Response update(@RequestBody LegendsListDTO legendsListDTO) {
        legendsListService.updateLegend(legendsListDTO);
        log.info("更新英雄列表:" + legendsListDTO.getName());

        return Response.success();
    }

    @PutMapping("/refresh")
    public Response refresh() {
        legendsListService.refreshList();
        log.info("刷新英雄列表");

        return Response.success();
    }
}
