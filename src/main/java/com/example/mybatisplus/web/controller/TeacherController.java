package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.TeacherService;
import com.example.mybatisplus.model.domain.Teacher;

import java.util.List;

@Controller
@RequestMapping("/api/teacher")
public class TeacherController {
    private final Logger logger = LoggerFactory.getLogger( TeacherController.class );

    @Autowired
    private TeacherService teacherService;

    @GetMapping("list")
    @ResponseBody
    public JsonResponse list(){
        List<Teacher> list = teacherService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("/listByKey")
    @ResponseBody
    public JsonResponse listByKey(@RequestBody Teacher teacher){
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Teacher::getTeacherId,teacher.getTeacherId());
        List<Teacher> list = teacherService.list(wrapper);
        return JsonResponse.success(list);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public JsonResponse updateById(@RequestBody Teacher teacher ){
        boolean b = teacherService.updateById(teacher);
        return JsonResponse.success(b);
    }

    @PostMapping("/insertById")
    @ResponseBody
    public JsonResponse insertById(@RequestBody Teacher teacher ){
        boolean save = teacherService.save(teacher);
        return JsonResponse.success(save);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody Teacher teacher ){
        boolean b = teacherService.removeById(teacher);
        return JsonResponse.success(b);
    }
}
