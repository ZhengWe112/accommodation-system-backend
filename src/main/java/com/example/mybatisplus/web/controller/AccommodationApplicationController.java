package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.AccommodationApplication;
import com.example.mybatisplus.model.domain.AccommodationLog;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.AccommodationApplicationService;
import com.example.mybatisplus.service.AccommodationLogService;
import com.example.mybatisplus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生住退宿申请表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-13 01:31:07
 */
@RestController
@RequestMapping("/api/accommodationApplication")
public class AccommodationApplicationController {

    @Autowired
    private AccommodationApplicationService accommodationApplicationService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AccommodationLogService accommodationLogService;

    @GetMapping("/list")
    public JsonResponse list(Long studentId, int pageNo, int pageSize){
        // 分页查找方法，获取给定id学生的所有申请
        LambdaQueryWrapper<AccommodationApplication> wrapper = new LambdaQueryWrapper<>();

        if (studentId != null){
            wrapper.eq(AccommodationApplication::getStudentId, studentId);
        }

        Page<AccommodationApplication> pageInfo = new Page<>(pageNo, pageSize);
        Page<AccommodationApplication> page = accommodationApplicationService.page(pageInfo, wrapper);

        List<AccommodationApplication> records = page.getRecords();
        records = records.stream().map(record -> {
            Student student = studentService.getById(record.getStudentId());
            return record.setStudentName(student.getFullname());
        }).collect(Collectors.toList());

        return JsonResponse.success(new PageResponseDTO<>(records, page.getTotal()));
    }

    @GetMapping("review")
    public JsonResponse review(Long id, Integer isAgree, Long responsibleLeaderId, String reason) {
        AccommodationApplication accommodationApplication = new AccommodationApplication();
        accommodationApplication.setId(id);

        if (isAgree == 0) {
            accommodationApplication.setState(1);
        } else {
            accommodationApplication.setState(2);
        }

        accommodationApplicationService.updateById(accommodationApplication);
        accommodationApplication = accommodationApplicationService.getById(id);

        AccommodationLog accommodationLog = new AccommodationLog();
        accommodationLog.setRequestReason(accommodationApplication.getRequestReason())
                .setResponsibleLeaderId(responsibleLeaderId)
                .setStudentId(accommodationApplication.getStudentId())
                .setRequestTime(accommodationApplication.getRequestTime())
                .setRequestType(accommodationApplication.getRequestType())
                .setReviewReason(reason)
                .setReviewTime(LocalDateTime.now())
                .setReviewState(isAgree == 1);

        accommodationLogService.save(accommodationLog);
        return JsonResponse.success("success");
    }
}

