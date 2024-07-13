package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Building;
import com.example.mybatisplus.model.domain.Room;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.BuildingService;
import com.example.mybatisplus.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 房间表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:26:17
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("list")
    public JsonResponse list(int pageNo, int pageSize) {
        Page<Room> page = roomService.page(new Page<>(pageNo, pageSize));

        List<Room> records = page.getRecords();

        records = records.stream().map(record -> {
            Building building = buildingService.getById(record.getBuildingId());
            return record.setBuildingName(building.getBuildingName());
        }).collect(Collectors.toList());

        return JsonResponse.success(new PageResponseDTO<>(records, page.getTotal()));
    }

}

