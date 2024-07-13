package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends BaseMapper<Student>{
    //Student selectByIdMY(@Param("student_id") char student_id);
    List<Student> selectByStudent(@Param("student") Student student);

    Student insertById(@Param("student") Student student);
}
