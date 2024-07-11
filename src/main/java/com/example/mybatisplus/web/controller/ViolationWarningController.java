package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ViolationWarning;
import com.example.mybatisplus.service.ViolationWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public JsonResponse<List<ViolationWarning>> listByStudentId(Long id) {
        LambdaQueryWrapper<ViolationWarning> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ViolationWarning::getStudentId, id);
        List<ViolationWarning> warnings = violationWarningService.list(wrapper);
        return JsonResponse.success(warnings);
    }
}

