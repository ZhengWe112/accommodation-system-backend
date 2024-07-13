package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.StudentService;
import com.example.mybatisplus.model.domain.Student;

import java.util.List;

@Controller
@RequestMapping("/api/student")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger( StudentController.class );
    @Autowired
    private StudentService studentService;

    @GetMapping("list")
    @ResponseBody
    public JsonResponse list(){
        List<Student> list = studentService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("/listByKey")
    @ResponseBody
    public JsonResponse listByKey(@RequestBody Student student){
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Student::getStudentId,student.getStudentId());
        List<Student> list = studentService.list(wrapper);
        return JsonResponse.success(list);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public JsonResponse updateById(@RequestBody Student student ){
        boolean b = studentService.updateById(student);
        return JsonResponse.success(b);
    }

    @PostMapping("/insertById")
    @ResponseBody
    public JsonResponse insertById(@RequestBody Student student ){
        boolean save = studentService.save(student);
        return JsonResponse.success(save);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody Student student ){
        boolean b = studentService.removeById(student);
        return JsonResponse.success(b);
    }
}
