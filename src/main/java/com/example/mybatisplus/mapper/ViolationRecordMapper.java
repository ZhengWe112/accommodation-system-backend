package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ViolationRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生违规记录表 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-11 07:35:49
 */
@Mapper
public interface ViolationRecordMapper extends BaseMapper<ViolationRecord> {

}
