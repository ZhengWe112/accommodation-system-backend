package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.MaintenanceAdministratorService;
import com.example.mybatisplus.service.MaintenanceRecordService;
import com.example.mybatisplus.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 维修管理员信息表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:54
 */
@RestController
@RequestMapping("/api/maintenanceAdministrator")
public class MaintenanceAdministratorController {

    @Autowired
    private MaintenanceAdministratorService maintenanceAdministratorService;
    @Autowired
    private MaintenanceRecordService maintenanceRecordService;

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    // 需求：维修管理员分配维修人员到场维修，修改维修申请状态，通知学生
    @PostMapping("/process")
    public JsonResponse<String> process(@RequestBody MaintenanceRequest maintenanceRequest,
                                        @RequestParam LocalDate maintenanceTime,
                                        @RequestParam String maintainerName) {
        // 处理申请记录
        maintenanceRequest.setState(1);
        boolean updatedFlag = maintenanceRequestService.updateById(maintenanceRequest);
        if (updatedFlag) {
            // 新增record
            MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
            maintenanceRecord.setStudentId(maintenanceRequest.getStudentId()).
                    setMaintenanceItem(maintenanceRequest.getRequestItem()).
                    setMaintenanceTime(maintenanceTime).
                    setDamageReason(maintenanceRequest.getReason()).
                    setMaintainerName(maintainerName);
            boolean addedFlag = maintenanceRecordService.save(maintenanceRecord); // save方法添加记录
            String addedRes;
            if (addedFlag) {
                addedRes="维修记录生成成功。";
            } else {
                addedRes="维修记录生成失败。";
            }
            return JsonResponse.success("处理维修申请成功，"+addedRes);
        } else {
            return JsonResponse.failure("处理维修申请失败。");
        }
    }

    @GetMapping("/view")
    public JsonResponse view(@RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "10") int pageSize){
        // 调用分页查找方法，查看所有维修申请（未维修）
        Page<MaintenanceRequest> pageInfo = new Page<>(pageNo, pageSize);
        Page<MaintenanceRequest> page = maintenanceRequestService.page(pageInfo, null);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }

    @GetMapping("/list")
    public JsonResponse list(@RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "10") int pageSize){
        // 调用分页查找方法，查看所有已维修
        Page<MaintenanceRecord> pageInfo = new Page<>(pageNo, pageSize);
        Page<MaintenanceRecord> page = maintenanceRecordService.page(pageInfo, null);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }
    @GetMapping("/list1")
    @ResponseBody
    public JsonResponse list(){
        List<MaintenanceAdministrator> list =     maintenanceAdministratorService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("/listByKey")
    @ResponseBody
    public JsonResponse listByKey(@RequestBody MaintenanceAdministrator     maintenanceAdministrator){
        LambdaQueryWrapper<MaintenanceAdministrator> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(MaintenanceAdministrator::getJobId,     maintenanceAdministrator.getJobId());
        List<MaintenanceAdministrator> list =     maintenanceAdministratorService.list(wrapper);
        return JsonResponse.success(list);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public JsonResponse updateById(@RequestBody MaintenanceAdministrator     maintenanceAdministrator ){
        boolean b =     maintenanceAdministratorService.updateById(    maintenanceAdministrator);
        return JsonResponse.success(b);
    }

    @PostMapping("/insertById")
    @ResponseBody
    public JsonResponse insertById(@RequestBody MaintenanceAdministrator     maintenanceAdministrator ){
        boolean save =     maintenanceAdministratorService.save(    maintenanceAdministrator);
        return JsonResponse.success(save);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody MaintenanceAdministrator     maintenanceAdministrator ){
        boolean b =     maintenanceAdministratorService.removeById(    maintenanceAdministrator);
        return JsonResponse.success(b);
    }
}

