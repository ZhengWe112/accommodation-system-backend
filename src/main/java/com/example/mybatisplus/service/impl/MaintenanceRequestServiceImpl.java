package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.MaintenanceRequest;
import com.example.mybatisplus.mapper.MaintenanceRequestMapper;
import com.example.mybatisplus.service.MaintenanceRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 维修申请表 由学生发起的 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-13 04:34:27
 */
@Service
public class MaintenanceRequestServiceImpl extends ServiceImpl<MaintenanceRequestMapper, MaintenanceRequest> implements MaintenanceRequestService {

}
