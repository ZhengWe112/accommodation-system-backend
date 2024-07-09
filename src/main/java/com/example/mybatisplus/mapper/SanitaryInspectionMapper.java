package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.DormSanitaryInspectionLog;
import com.example.mybatisplus.model.domain.SanitaryInspection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 卫生检查表 此表只是记录了一次卫生检查的整体情况 明细情况在表sanitary_inspection_record Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-08 01:46:28
 */
@Mapper
public interface SanitaryInspectionMapper extends BaseMapper<SanitaryInspection> {

    List<DormSanitaryInspectionLog> countScore(Long sanitaryInspectionId);
}
