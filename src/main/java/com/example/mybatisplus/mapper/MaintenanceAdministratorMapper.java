package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.MaintenanceAdministrator;
import com.example.mybatisplus.model.domain.SystemAdministrator;
import com.example.mybatisplus.model.domain.ResponsibleLeader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceAdministratorMapper extends BaseMapper<MaintenanceAdministrator>{
    List<MaintenanceAdministrator> selectByManager1(@Param("manager") MaintenanceAdministrator manager);
    List<SystemAdministrator> selectByManager2(@Param("manager") SystemAdministrator manager);
    List<ResponsibleLeader> selectByManager3(@Param("manager") ResponsibleLeader manager);
}

