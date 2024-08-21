package com.czn.assistant.controller;

import com.czn.assistant.dto.response.LegendsListDTO;
import com.czn.assistant.service.impl.LegendsListService;
import com.czn.assistant.util.LegendsNameConverter;
import com.czn.assistant.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legendsList")
@Slf4j
public class LegendsListController {

    LegendsListService legendsListService;
    LegendsNameConverter legendsNameConverter;

    @Autowired
    public LegendsListController(LegendsListService legendsListService, LegendsNameConverter legendsNameConverter) {
        this.legendsListService = legendsListService;
        this.legendsNameConverter = legendsNameConverter;
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

    //根据映射文件更新英雄chineseName
    //返回成功更新数据条数
    @PutMapping("/updateChineseName")
    public Response<Integer> updateChineseNameBasedOnMap() {
        Integer i = legendsNameConverter.updateLegendsChineseNameBasedOnLegendsNameMapFile();

        return Response.success(i);
    }

    //将数据库中的英雄英文与中文名写入映射文件
    @PutMapping("/writeMapFile")
    public Response writeMapFileBasedOnDataBase() {
        legendsNameConverter.writeLegendsNameToFile();

        return Response.success();
    }
}
