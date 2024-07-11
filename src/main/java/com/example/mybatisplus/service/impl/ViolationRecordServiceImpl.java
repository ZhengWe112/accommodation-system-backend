package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.domain.ViolationRecord;
import com.example.mybatisplus.mapper.ViolationRecordMapper;
import com.example.mybatisplus.service.StudentService;
import com.example.mybatisplus.service.ViolationRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生违规记录表 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-11 07:35:49
 */
@Service
public class ViolationRecordServiceImpl extends ServiceImpl<ViolationRecordMapper, ViolationRecord> implements ViolationRecordService {

    @Autowired
    private StudentService studentService;

    @Override
    public List<ViolationRecord> listWithStudent(Wrapper<ViolationRecord> wrapper) {
        List<ViolationRecord> records = this.list(wrapper);
        records = records.stream().map(record -> {
            Student student = studentService.getById(record.getStudentId());
            return record.setStudentName(student.getFullname());
        }).collect(Collectors.toList());
        return records;
    }
}
