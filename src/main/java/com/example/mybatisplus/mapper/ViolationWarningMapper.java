package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ViolationWarning;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生违规警告表 这是分管领导发给学生的 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-11 09:44:01
 */
@Mapper
public interface ViolationWarningMapper extends BaseMapper<ViolationWarning> {

}
