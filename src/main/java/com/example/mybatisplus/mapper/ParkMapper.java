package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Park;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 园区表 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-12 05:37:56
 */
@Mapper
public interface ParkMapper extends BaseMapper<Park> {

}
