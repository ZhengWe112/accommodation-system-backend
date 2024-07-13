package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ResponsibleLeader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 分管领导表 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:54
 */
@Mapper
public interface ResponsibleLeaderMapper extends BaseMapper<ResponsibleLeader> {
    List<ResponsibleLeader> selectByManager3(@Param("responsibleLeader") ResponsibleLeader responsibleLeader);
}
