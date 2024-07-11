package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.DormitoryRatingDictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 寝室评分字典 怎么去计算寝室的得分 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-10 01:53:12
 */
@Mapper
public interface DormitoryRatingDictionaryMapper extends BaseMapper<DormitoryRatingDictionary> {

}
