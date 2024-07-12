package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.AccommodationApplication;
import com.example.mybatisplus.model.domain.AccommodationLog;
import com.example.mybatisplus.model.domain.AccommodationNotification;
import com.example.mybatisplus.model.domain.SanitationObjectionReviewResultNotification;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.AccommodationApplicationService;
import com.example.mybatisplus.service.AccommodationLogService;
import com.example.mybatisplus.service.AccommodationNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 分管领导表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:54
 */
@RestController
@RequestMapping("/api/responsibleLeader")
public class ResponsibleLeaderController {

    @Autowired
    private AccommodationApplicationService accommodationApplicationService;

    @Autowired
    private AccommodationLogService accommodationLogService;

    // 需求：审核由分管领导执行，审核结束后需要将申请状态返回给学生，分管领导可以通过或者驳回申请，申请状态变为“通过”或者“驳回”。
    // 对应到表中state:申请状态 0表示等待审核 1表示审核通过 2表示被驳回
    @PostMapping("/accommodationApplication")
    public JsonResponse<String> reviewAccommodationApplication(@RequestBody AccommodationLog accommodationLog) {
        // 方法名中 review => "审查"
        // 注意:参数使用的是AccommodationLog作为requestBody,前端请求时要将数据封装成为AccommodationLog
        // 只需在AccommodationApplication的JSON中添加字段responsibleLeaderId、reviewTime、reviewState、reviewReason(null)即可，
        // 同时要去掉原有的state和自动生成的创建时间和更新时间
        // -------------------------------------------------------------------------------------------------------------
        // 因此获取申请记录时，要通过studentId和requestTime去获取唯一记录，一个人一瞬间只能发一个请求
        LambdaQueryWrapper<AccommodationApplication> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AccommodationApplication::getStudentId, accommodationLog.getStudentId()).
                eq(AccommodationApplication::getRequestTime, accommodationLog.getRequestTime());
        // 获取到唯一申请记录
        AccommodationApplication accommodationApplication = accommodationApplicationService.getOne(wrapper);
        // 更新申请记录
        // 三目运算符将Boolean转Integer
        // AccommodationApplication中 申请状态 0表示等待审核 1表示审核通过 2表示被驳回
        // AccommodationLog中 申请是通过还是驳回 0通过 1驳回
        accommodationApplication.setState(accommodationLog.getReviewState()?2:1);
        // 更新住退宿申请
        boolean updatedFlag = accommodationApplicationService.updateById(accommodationApplication);
        if (updatedFlag) {
            // 住退宿申请更新完后，新增log
            boolean addedFlag = accommodationLogService.save(accommodationLog); // save方法添加记录
            String addedRes;
            if (addedFlag) {
                addedRes="操作记录生成成功。";
            } else {
                addedRes="操作记录生成失败。";
            }
            return JsonResponse.success("审核住退宿申请成功，"+addedRes);
        } else {
            return JsonResponse.failure("审核住退宿申请添加失败。");
        }
    }

    @GetMapping("/accommodationApplication/{id}")
    public JsonResponse list(@PathVariable Long id,
                             @RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "10") int pageSize){
        // 调用AccommodationLogController的分页查找方法，获取给定id分管领导的所有申请
        LambdaQueryWrapper<AccommodationLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AccommodationLog::getResponsibleLeaderId, id);
        Page<AccommodationLog> pageInfo = new Page<>(pageNo, pageSize);
        Page<AccommodationLog> page = accommodationLogService.page(pageInfo, wrapper);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }
}

