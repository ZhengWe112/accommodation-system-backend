package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Building;
import com.example.mybatisplus.model.domain.DormitoryAdministrator;
import com.example.mybatisplus.model.domain.Park;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.BuildingService;
import com.example.mybatisplus.service.DormitoryAdministratorService;
import com.example.mybatisplus.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 楼栋表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-12 11:24:54
 */
@RestController
@RequestMapping("/api/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private DormitoryAdministratorService dormitoryAdministratorService;

    @Autowired
    private ParkService parkService;

    @GetMapping("/list")
    public JsonResponse list(int pageNo, int pageSize) {
        Page<Building> page = buildingService.page(new Page<>(pageNo, pageSize));

        List<Building> records = page.getRecords();

        records = records.stream().map(record -> {
            DormitoryAdministrator dormitoryAdministrator = dormitoryAdministratorService.getById(record.getDormitoryAdministratorId());
            record.setDormitoryAdministratorName(dormitoryAdministrator.getFullname());
            Park park = parkService.getById(record.getParkId());
            return record.setParkName(park.getParkName());
        }).collect(Collectors.toList());

        return JsonResponse.success(new PageResponseDTO<>(records, page.getTotal()));
    }
}

