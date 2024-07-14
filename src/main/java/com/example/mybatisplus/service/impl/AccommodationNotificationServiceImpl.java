package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.mapper.BuildingMapper;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.model.domain.AccommodationLog;
import com.example.mybatisplus.model.domain.AccommodationNotification;
import com.example.mybatisplus.mapper.AccommodationNotificationMapper;
import com.example.mybatisplus.model.domain.Building;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.service.AccommodationNotificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void sendNotification(AccommodationLog accommodationLog, Long preId, Long nowId) {
        Student student = studentMapper.selectById(accommodationLog.getStudentId());

        switch (accommodationLog.getRequestType()) {
            case 0 -> {
                Building building = buildingMapper.selectById(nowId);
                this.save(new AccommodationNotification(student.getFullname(), 2, building.getDormitoryAdministratorId()));
            }
            case 1 -> {
                if (Objects.equals(preId, nowId)) {
                    Building building = buildingMapper.selectById(nowId);
                    this.save(new AccommodationNotification(student.getFullname(), 4, building.getDormitoryAdministratorId()));
                } else {
                    Building building1 = buildingMapper.selectById(preId);
                    Building building2 = buildingMapper.selectById(nowId);
                    this.save(new AccommodationNotification(student.getFullname(), 3, building1.getDormitoryAdministratorId()));
                    this.save(new AccommodationNotification(student.getFullname(), 2, building2.getDormitoryAdministratorId()));

                }
            }
            case 2 -> {
                // 互换
            }
            case 3, 4 -> {
                Building building = buildingMapper.selectById(preId);
                this.save(new AccommodationNotification(student.getFullname(), 3, building.getDormitoryAdministratorId()));
            }
        }
    }
}
