package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Building;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 楼栋表 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-12 11:24:54
 */
@Mapper
public interface BuildingMapper extends BaseMapper<Building> {

}
