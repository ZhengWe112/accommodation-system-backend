package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:30
 */
public interface StudentService extends IService<Student> {

    // 获取某位学生的房间号
    Long getRoomId(Long studentId);
}
