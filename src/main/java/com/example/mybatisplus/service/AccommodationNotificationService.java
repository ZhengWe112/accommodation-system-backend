package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.AccommodationLog;
import com.example.mybatisplus.model.domain.AccommodationNotification;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 住退宿通知表 这是分管领导发给宿舍管理员的通知 服务类
 * </p>
 *
 * @author team01
 * @since 2024-07-13 03:16:46
 */
public interface AccommodationNotificationService extends IService<AccommodationNotification> {

    void sendNotification(AccommodationLog accommodationLog, Long preId, Long nowId);
}
