package com.czn.assistant.controller.admin;

import com.czn.assistant.common.enums.ResponseCodeEnum;
import com.czn.assistant.dao.entity.LegendsList;
import com.czn.assistant.dto.response.LegendsListsDTO;
import com.czn.assistant.service.impl.LegendsListService;
import com.czn.assistant.util.Response;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class LegendsListController {

    LegendsListService legendsListService;

    @Autowired
    public LegendsListController(LegendsListService legendsListService) {
        this.legendsListService = legendsListService;
    }

    @GetMapping("/getLegendsList")
    public Response getLegendsList() {
        LegendsListsDTO legendsList = legendsListService.getLegendsList();
        if (legendsList == null) {
            return Response.fail(ResponseCodeEnum.Legends_List_INVALID_ERROR.getErrorCode(), ResponseCodeEnum.Legends_List_INVALID_ERROR.getErrorMessage());
        }
        return Response.success(legendsList);
    }
}
