package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ResponsibleLeader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsibleLeaderMapper extends BaseMapper<ResponsibleLeader>{

    List<ResponsibleLeader> selectByManager3(@Param("manager") ResponsibleLeader manager);
}
