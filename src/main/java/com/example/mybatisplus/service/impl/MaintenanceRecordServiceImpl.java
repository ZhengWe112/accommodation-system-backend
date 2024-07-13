package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.MaintenanceRecord;
import com.example.mybatisplus.mapper.MaintenanceRecordMapper;
import com.example.mybatisplus.service.MaintenanceRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 维修记录表 记录了所有维修的历史 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-13 04:34:16
 */
@Service
public class MaintenanceRecordServiceImpl extends ServiceImpl<MaintenanceRecordMapper, MaintenanceRecord> implements MaintenanceRecordService {

}
