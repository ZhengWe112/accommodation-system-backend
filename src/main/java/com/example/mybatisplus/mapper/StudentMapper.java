package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-07 04:44:24
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
