package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.AccommodationLog;
import com.example.mybatisplus.model.domain.SanitationObjectionReviewResultNotification;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.AccommodationLogService;
import com.example.mybatisplus.service.SanitationObjectionReviewResultNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 住退宿日志 记录了所有学生的所有历史申请 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-13 01:31:36
 */
@RestController
@RequestMapping("/api/accommodationLog")
public class AccommodationLogController {

    @Autowired
    private AccommodationLogService accommodationLogService;

    @GetMapping("/list")
    public JsonResponse list(@RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "10") int pageSize){
        // 分页查找方法
        Page<AccommodationLog> pageInfo = new Page<>(pageNo, pageSize);
        Page<AccommodationLog> page = accommodationLogService.page(pageInfo, null);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }
}

