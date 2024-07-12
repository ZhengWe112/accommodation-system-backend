package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.DormSanitaryInspectionLog;
import com.example.mybatisplus.model.domain.SanitaryInspection;
import com.example.mybatisplus.model.domain.SanitaryInspectionRecord;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.DormSanitaryInspectionLogService;
import com.example.mybatisplus.service.SanitaryInspectionRecordService;
import com.example.mybatisplus.service.SanitaryInspectionService;
import com.example.mybatisplus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 宿舍卫生检查日志表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-09 09:20:04
 */
@RestController
@RequestMapping("/api/dormSanitaryInspectionLog")
public class DormSanitaryInspectionLogController {

    @Autowired
    DormSanitaryInspectionLogService dormSanitaryInspectionLogService;

    @Autowired
    SanitaryInspectionRecordService sanitaryInspectionRecordService;

    @Autowired
    private SanitaryInspectionService sanitaryInspectionService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public JsonResponse list(Integer pageNo, Integer pageSize, Long dormAdminId, Long studentId) {
        LambdaQueryWrapper<DormSanitaryInspectionLog> wrapper = new LambdaQueryWrapper<>();

        if (dormAdminId != null) {
            LambdaQueryWrapper<SanitaryInspection> sanitaryInspectionWrapper = new LambdaQueryWrapper<>();
            sanitaryInspectionWrapper.eq(SanitaryInspection::getDormitoryAdministratorId, dormAdminId);
            List<SanitaryInspection> sanitaryInspections = sanitaryInspectionService.list(sanitaryInspectionWrapper);
            List<Long> ids = sanitaryInspections.stream().map(SanitaryInspection::getId).collect(Collectors.toList());
            wrapper.in(DormSanitaryInspectionLog::getSanitaryInspectionId, ids);
        }

        if (studentId != null) {
            Long roomId = studentService.getRoomId(studentId);
            wrapper.eq(DormSanitaryInspectionLog::getRoomId, roomId);
        }

        Page<DormSanitaryInspectionLog> pageInfo = new Page<>(pageNo, pageSize);

        Page<DormSanitaryInspectionLog> logPage = dormSanitaryInspectionLogService.page(pageInfo, wrapper);

        List<DormSanitaryInspectionLog> logs = logPage.getRecords().stream().map(log -> {
            LambdaQueryWrapper<SanitaryInspectionRecord> recordWrapper = new LambdaQueryWrapper<>();
            recordWrapper.eq(SanitaryInspectionRecord::getSanitaryInspectionId, log.getSanitaryInspectionId())
                    .eq(SanitaryInspectionRecord::getRoomId, log.getRoomId());
            List<SanitaryInspectionRecord> inspectionRecords = sanitaryInspectionRecordService.list(recordWrapper);
            return log.setSanitaryInspectionDetail(inspectionRecords);
        }).collect(Collectors.toList());

        return JsonResponse.success(new PageResponseDTO<>(logs, logPage.getTotal()));
    }
}

