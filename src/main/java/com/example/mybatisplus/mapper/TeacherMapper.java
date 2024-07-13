package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper extends BaseMapper<Teacher>{
    List<Teacher> selectByTeacher(@Param("teacher") Teacher teacher);
}
