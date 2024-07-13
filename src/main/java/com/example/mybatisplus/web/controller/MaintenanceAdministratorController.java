package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.model.domain.MaintenanceAdministrator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.MaintenanceAdministratorService;

import java.util.List;

@Controller
@RequestMapping("/api/maintenance_administrator")
public class MaintenanceAdministratorController {

    private final Logger logger = LoggerFactory.getLogger( MaintenanceAdministratorController.class );
    @Autowired
    private MaintenanceAdministratorService managerService;

    @GetMapping("/list")
    @ResponseBody
    public JsonResponse list(){
        List<MaintenanceAdministrator> list = managerService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("/listByKey")
    @ResponseBody
    public JsonResponse listByKey(@RequestBody MaintenanceAdministrator manager){
        LambdaQueryWrapper<MaintenanceAdministrator> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(MaintenanceAdministrator::getJobId, manager.getJobId());
        List<MaintenanceAdministrator> list = managerService.list(wrapper);
        return JsonResponse.success(list);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public JsonResponse updateById(@RequestBody MaintenanceAdministrator manager ){
        boolean b = managerService.updateById(manager);
        return JsonResponse.success(b);
    }

    @PostMapping("/insertById")
    @ResponseBody
    public JsonResponse insertById(@RequestBody MaintenanceAdministrator manager ){
        boolean save = managerService.save(manager);
        return JsonResponse.success(save);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody MaintenanceAdministrator manager ){
        boolean b = managerService.removeById(manager);
        return JsonResponse.success(b);
    }

}
