package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.MaintenanceAdministrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 维修管理员信息表 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:54
 */
@Mapper
public interface MaintenanceAdministratorMapper extends BaseMapper<MaintenanceAdministrator> {
    List<MaintenanceAdministrator> selectByManager1(@Param("maintenanceAdministrator") MaintenanceAdministrator maintenanceAdministrator);
}
