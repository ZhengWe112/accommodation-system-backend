package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.AccommodationNotification;
import com.example.mybatisplus.mapper.AccommodationNotificationMapper;
import com.example.mybatisplus.service.AccommodationNotificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 住退宿通知表 这是分管领导发给宿舍管理员的通知 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-13 03:16:46
 */
@Service
public class AccommodationNotificationServiceImpl extends ServiceImpl<AccommodationNotificationMapper, AccommodationNotification> implements AccommodationNotificationService {

}
