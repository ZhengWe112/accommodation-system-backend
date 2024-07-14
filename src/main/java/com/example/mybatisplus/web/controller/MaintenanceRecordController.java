package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.MaintenanceRecord;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.MaintenanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 维修记录表 记录了所有维修的历史 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-13 04:34:16
 */
@RestController
@RequestMapping("/api/maintenanceRecord")
public class MaintenanceRecordController {

    @Autowired
    private MaintenanceRecordService maintenanceRecordService;

    @GetMapping("/list")
    public JsonResponse list(int pageNo, int pageSize, Long studentId) {
        LambdaQueryWrapper<MaintenanceRecord> wrapper = new LambdaQueryWrapper<>();

        if (studentId != null) {
            wrapper.eq(MaintenanceRecord::getStudentId, studentId);
        }

        Page<MaintenanceRecord> page = maintenanceRecordService.page(new Page<>(pageNo, pageSize), wrapper);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }
}

