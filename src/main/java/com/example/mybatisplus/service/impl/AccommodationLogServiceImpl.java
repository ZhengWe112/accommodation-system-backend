package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.AccommodationLog;
import com.example.mybatisplus.mapper.AccommodationLogMapper;
import com.example.mybatisplus.service.AccommodationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 住退宿日志 记录了所有学生的所有历史申请 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-13 01:31:36
 */
@Service
public class AccommodationLogServiceImpl extends ServiceImpl<AccommodationLogMapper, AccommodationLog> implements AccommodationLogService {

}
