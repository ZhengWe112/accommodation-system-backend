package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ViolationWarning;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.ViolationWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 学生违规警告表 这是分管领导发给学生的 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-11 09:44:01
 */
@RestController
@RequestMapping("/api/violationWarning")
public class ViolationWarningController {

    @Autowired
    private ViolationWarningService violationWarningService;

    @GetMapping("list")
    public JsonResponse listByStudentId(int pageNo, int pageSize, Long id) {
        LambdaQueryWrapper<ViolationWarning> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ViolationWarning::getStudentId, id);

        Page<ViolationWarning> pageInfo = new Page<>(pageNo, pageSize);

        Page<ViolationWarning> warningPage = violationWarningService.page(pageInfo, wrapper);

        return JsonResponse.success(new PageResponseDTO<>(warningPage.getRecords(), warningPage.getTotal()));
    }
}

