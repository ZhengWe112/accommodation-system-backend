package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.AccommodationNotification;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.AccommodationNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 住退宿通知表 这是分管领导发给宿舍管理员的通知 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-13 03:16:46
 */
@RestController
@RequestMapping("/api/accommodationNotification")
public class AccommodationNotificationController {

    @Autowired
    private AccommodationNotificationService accommodationNotificationService;

    @GetMapping("list")
    public JsonResponse list(int pageNo, int pageSize, Long id) {
        LambdaQueryWrapper<AccommodationNotification> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AccommodationNotification::getDormitoryAdministratorId, id);
        Page<AccommodationNotification> page = accommodationNotificationService.page(new Page<>(pageNo, pageSize), wrapper);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }
}

