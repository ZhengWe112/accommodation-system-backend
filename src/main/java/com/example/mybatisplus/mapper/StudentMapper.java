package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:30
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    Long getRoomId(@Param("studentId") Long studentId);
}
