package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.mapper.TeacherMapper;
import com.example.mybatisplus.model.domain.Teacher;
import com.example.mybatisplus.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

}
