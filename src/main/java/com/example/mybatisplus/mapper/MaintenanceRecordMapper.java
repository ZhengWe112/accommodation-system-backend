package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.MaintenanceRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 维修记录表 记录了所有维修的历史 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-13 04:34:16
 */
@Mapper
public interface MaintenanceRecordMapper extends BaseMapper<MaintenanceRecord> {

}
