package com.example.mybatisplus.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.model.domain.ResponsibleLeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ResponsibleLeaderService;

import java.util.List;

@Controller
@RequestMapping("/api/responsible_leader")
public class ResponsibleLeaderController {

    private final Logger logger = LoggerFactory.getLogger( ResponsibleLeaderController.class );
    @Autowired
    private ResponsibleLeaderService managerService;

    @GetMapping("/list")
    @ResponseBody
    public JsonResponse list(){
        List<ResponsibleLeader> list = managerService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("/listByKey")
    @ResponseBody
    public JsonResponse listByKey(@RequestBody ResponsibleLeader manager){
        LambdaQueryWrapper<ResponsibleLeader> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ResponsibleLeader::getJobId, manager.getJobId());
        List<ResponsibleLeader> list = managerService.list(wrapper);
        return JsonResponse.success(list);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public JsonResponse updateById(@RequestBody ResponsibleLeader manager ){
        boolean b = managerService.updateById(manager);
        return JsonResponse.success(b);
    }

    @PostMapping("/insertById")
    @ResponseBody
    public JsonResponse insertById(@RequestBody ResponsibleLeader manager ){
        boolean save = managerService.save(manager);
        return JsonResponse.success(save);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody ResponsibleLeader manager ){
        boolean b = managerService.removeById(manager);
        return JsonResponse.success(b);
    }

}
