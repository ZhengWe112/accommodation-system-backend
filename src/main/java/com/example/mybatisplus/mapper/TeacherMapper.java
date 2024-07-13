package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:15:07
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    List<Teacher> selectByTeacher(@Param("teacher") Teacher teacher);
}
