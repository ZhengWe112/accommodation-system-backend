package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.MaintenanceRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 维修申请表 由学生发起的 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-13 04:34:27
 */
@Mapper
public interface MaintenanceRequestMapper extends BaseMapper<MaintenanceRequest> {

}
