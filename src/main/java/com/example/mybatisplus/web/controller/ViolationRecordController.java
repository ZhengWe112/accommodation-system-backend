package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.domain.ViolationRecord;
import com.example.mybatisplus.model.domain.ViolationWarning;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.StudentService;
import com.example.mybatisplus.service.ViolationRecordService;
import com.example.mybatisplus.service.ViolationWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生违规记录表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-11 07:35:49
 */
@RestController
@RequestMapping("/api/violationRecord")
public class ViolationRecordController {

    @Autowired
    private ViolationRecordService violationRecordService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ViolationWarningService violationWarningService;

    @GetMapping("byDormAdmin")
    public JsonResponse getByDorm(Long id, int pageNo, int pageSize) {
        LambdaQueryWrapper<ViolationRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ViolationRecord::getDormitoryAdministratorId, id);

        Page<ViolationRecord> pageInfo = new Page<>(pageNo, pageSize);

        Page<ViolationRecord> recordPage = violationRecordService.page(pageInfo, wrapper);

        List<ViolationRecord> records = recordPage.getRecords();
        records = records.stream().map(record -> {
            Student student = studentService.getById(record.getStudentId());
            return record.setStudentName(student.getFullname());
        }).collect(Collectors.toList());

        return JsonResponse.success(new PageResponseDTO<>(records, recordPage.getTotal()));
    }

    @PostMapping("/add")
    public JsonResponse<String> add(@RequestBody ViolationRecord violationRecord) {
        LambdaQueryWrapper<Student> studentWrapper = new LambdaQueryWrapper<>();
        studentWrapper.eq(Student::getStudentId, violationRecord.getStudentNumber());
        Student student = studentService.getOne(studentWrapper);

        if (student == null) {
            return JsonResponse.failure("找不到这个学生");
        }

        violationRecord.setStudentId(student.getId())
                .setDormitoryAdministratorId(SessionUtils.getCurrentUserInfo().getId());

        violationRecordService.save(violationRecord);
        return JsonResponse.success("success");
    }

    @GetMapping("/submit")
    public JsonResponse<String> submit(Long id) {
        ViolationRecord record = new ViolationRecord();
        record.setId(id).setState(1);
        violationRecordService.updateById(record);
        return JsonResponse.success("success");
    }

    @DeleteMapping("{id}")
    public JsonResponse<String> deleteById(@PathVariable Long id) {
        violationRecordService.removeById(id);
        return JsonResponse.success("success");
    }

    @GetMapping("byState")
    public JsonResponse getByState(int pageNo, int pageSize) {
        LambdaQueryWrapper<ViolationRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(ViolationRecord::getState, 1);

        Page<ViolationRecord> pageInfo = new Page<>(pageNo, pageSize);

        Page<ViolationRecord> page = violationRecordService.page(pageInfo, wrapper);

        List<ViolationRecord> records = page.getRecords();
        records = records.stream().map(record -> {
            Student student = studentService.getById(record.getStudentId());
            return record.setStudentName(student.getFullname());
        }).collect(Collectors.toList());

        return JsonResponse.success(new PageResponseDTO<>(records, page.getTotal()));
    }

    @GetMapping("send")
    public JsonResponse<String> sendWarningMsg(Long id) {
        ViolationRecord record = new ViolationRecord();
        record.setId(id).setState(2);
        violationRecordService.updateById(record);

        record = violationRecordService.getById(id);

        ViolationWarning violationWarning = new ViolationWarning();
        violationWarning.setViolationTime(record.getViolationTime())
                .setDegree(record.getViolationDegree())
                .setStudentId(record.getStudentId())
                .setDescription("违规项目：" + record.getViolationItem() + "  理由：" + record.getReason());
        violationWarningService.save(violationWarning);
        return JsonResponse.success("success");
    }
}

