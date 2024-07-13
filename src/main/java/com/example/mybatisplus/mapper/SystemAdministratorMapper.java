package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.SystemAdministrator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemAdministratorMapper extends BaseMapper<SystemAdministrator>{
    List<SystemAdministrator> selectByManager2(@Param("manager") SystemAdministrator manager);
}

