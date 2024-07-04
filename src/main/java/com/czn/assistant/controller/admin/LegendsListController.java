package com.czn.assistant.controller.admin;

import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.dao.entity.LegendsList;
import com.czn.assistant.dto.response.LegendsListDTO;
import com.czn.assistant.dto.response.LegendsListsDTO;
import com.czn.assistant.service.impl.LegendsListService;
import com.czn.assistant.util.Response;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class LegendsListController {

    LegendsListService legendsListService;

    @Autowired
    public LegendsListController(LegendsListService legendsListService) {
        this.legendsListService = legendsListService;
    }

    @GetMapping("/get/{name}")
    public Response<LegendsListDTO> get(@PathVariable("name") String name) {
        LegendsListDTO result = legendsListService.getLegend(name);

        return Response.success(result);
    }

    @GetMapping("/getLegendsList")
    public Response<List<LegendsListDTO>> getLegendsList() {
        List<LegendsListDTO> legendsList = legendsListService.getLegendsList();

        return Response.success(legendsList);
    }


}
