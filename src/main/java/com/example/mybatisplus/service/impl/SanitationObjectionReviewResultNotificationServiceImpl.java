package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.SanitaryInspection;
import com.example.mybatisplus.model.domain.SanitationObjection;
import com.example.mybatisplus.model.domain.SanitationObjectionReviewResultNotification;
import com.example.mybatisplus.mapper.SanitationObjectionReviewResultNotificationMapper;
import com.example.mybatisplus.service.SanitaryInspectionService;
import com.example.mybatisplus.service.SanitationObjectionReviewResultNotificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 卫生异议审核结果通知表 这是分管领导发给宿管的通知 表示这次异议给不给过 若过了宿管要修改相应的得分 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:38:35
 */
@Service
public class SanitationObjectionReviewResultNotificationServiceImpl extends ServiceImpl<SanitationObjectionReviewResultNotificationMapper, SanitationObjectionReviewResultNotification> implements SanitationObjectionReviewResultNotificationService {

    @Autowired
    private SanitaryInspectionService sanitaryInspectionService;

    @Autowired
    private SanitationObjectionReviewResultNotificationMapper sanitationObjectionReviewResultNotificationMapper;

    @Override
    public void sendNotification(SanitationObjection sanitationObjection) {
        SanitationObjectionReviewResultNotification notification = new SanitationObjectionReviewResultNotification();
        if (sanitationObjection.getState() == 3) {
            notification.setState(0);
        } else {
            notification.setState(1);
        }

        SanitaryInspection sanitaryInspection = sanitaryInspectionService.getById(sanitationObjection.getSanitaryInspectionId());
        notification.setDormitoryAdministratorId(sanitaryInspection.getDormitoryAdministratorId());

        notification.setDescription("卫生检查编号：" + sanitationObjection.getSanitaryInspectionId() + "   房间号：" + sanitationObjection.getRoomNumber() + "    理由：" + sanitationObjection.getObjectionReason());
        sanitationObjectionReviewResultNotificationMapper.insert(notification);
    }
}
