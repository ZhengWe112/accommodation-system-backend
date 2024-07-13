package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Park;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 园区表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-12 05:37:56
 */
@RestController
@RequestMapping("/api/park")
public class ParkController {

    @Autowired
    private ParkService parkService;

    @GetMapping("/list")
    public JsonResponse list(int pageNo, int pageSize) {
        Page<Park> page = parkService.page(new Page<>(pageNo, pageSize));
        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }

}

