package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.AccommodationNotification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 住退宿通知表 这是分管领导发给宿舍管理员的通知 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-13 03:16:46
 */
@Mapper
public interface AccommodationNotificationMapper extends BaseMapper<AccommodationNotification> {

}
