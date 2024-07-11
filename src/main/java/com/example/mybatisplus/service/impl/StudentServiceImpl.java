package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:30
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Long getRoomId(Long studentId) {
        return studentMapper.getRoomId(studentId);
    }
}
