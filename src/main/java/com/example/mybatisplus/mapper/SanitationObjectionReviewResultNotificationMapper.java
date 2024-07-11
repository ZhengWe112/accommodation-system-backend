package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.SanitationObjectionReviewResultNotification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 卫生异议审核结果通知表 这是分管领导发给宿管的通知 表示这次异议给不给过 若过了宿管要修改相应的得分 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:38:35
 */
@Mapper
public interface SanitationObjectionReviewResultNotificationMapper extends BaseMapper<SanitationObjectionReviewResultNotification> {

}
