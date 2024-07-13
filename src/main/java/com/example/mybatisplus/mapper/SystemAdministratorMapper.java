package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.SystemAdministrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统管理员 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:30
 */
@Mapper
public interface SystemAdministratorMapper extends BaseMapper<SystemAdministrator> {
    List<SystemAdministrator> selectByManager2(@Param("systemAdministrator") SystemAdministrator systemAdministrator);
}
