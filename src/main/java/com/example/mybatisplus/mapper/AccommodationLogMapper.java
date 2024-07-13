package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.AccommodationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 住退宿日志 记录了所有学生的所有历史申请 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-13 01:31:36
 */
@Mapper
public interface AccommodationLogMapper extends BaseMapper<AccommodationLog> {

}
