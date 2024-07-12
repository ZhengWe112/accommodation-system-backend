package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.SanitationObjectionReviewResultNotification;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.SanitationObjectionReviewResultNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 卫生异议审核结果通知表 这是分管领导发给宿管的通知 表示这次异议给不给过 若过了宿管要修改相应的得分 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:38:35
 */
@RestController
@RequestMapping("/api/sanitationObjectionReviewResultNotification")
public class SanitationObjectionReviewResultNotificationController {

    @Autowired
    private SanitationObjectionReviewResultNotificationService sanitationObjectionReviewResultNotificationService;

    @GetMapping("{id}")
    public JsonResponse list(@PathVariable Long id, int pageNo, int pageSize) {
        LambdaQueryWrapper<SanitationObjectionReviewResultNotification> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SanitationObjectionReviewResultNotification::getDormitoryAdministratorId, id);

        Page<SanitationObjectionReviewResultNotification> pageInfo = new Page<>(pageNo, pageSize);
        Page<SanitationObjectionReviewResultNotification> page = sanitationObjectionReviewResultNotificationService.page(pageInfo, wrapper);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }

    @GetMapping("/process")
    public JsonResponse<String> process(Long id) {
        SanitationObjectionReviewResultNotification notification = new SanitationObjectionReviewResultNotification();
        notification.setState(2).setId(id);
        sanitationObjectionReviewResultNotificationService.updateById(notification);
        return JsonResponse.success("success");
    }
}

