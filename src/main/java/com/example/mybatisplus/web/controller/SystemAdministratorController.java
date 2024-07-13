package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.model.domain.SystemAdministrator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.SystemAdministratorService;

import java.util.List;
@Controller
@RequestMapping("/api/system_administrator")
public class SystemAdministratorController {

    private final Logger logger = LoggerFactory.getLogger( SystemAdministratorController.class );
    @Autowired
    private SystemAdministratorService managerService;

    @GetMapping("/list")
    @ResponseBody
    public JsonResponse list(){
        List<SystemAdministrator> list = managerService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("/listByKey")
    @ResponseBody
    public JsonResponse listByKey(@RequestBody SystemAdministrator manager){
        LambdaQueryWrapper<SystemAdministrator> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(SystemAdministrator::getJobId, manager.getJobId());
        List<SystemAdministrator> list = managerService.list(wrapper);
        return JsonResponse.success(list);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public JsonResponse updateById(@RequestBody SystemAdministrator manager ){
        boolean b = managerService.updateById(manager);
        return JsonResponse.success(b);
    }

    @PostMapping("/insertById")
    @ResponseBody
    public JsonResponse insertById(@RequestBody SystemAdministrator manager ){
        boolean save = managerService.save(manager);
        return JsonResponse.success(save);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody SystemAdministrator manager ){
        boolean b = managerService.removeById(manager);
        return JsonResponse.success(b);
    }

}
