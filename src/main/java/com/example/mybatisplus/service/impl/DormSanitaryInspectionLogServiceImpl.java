package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.mapper.SanitaryInspectionMapper;
import com.example.mybatisplus.model.domain.DormSanitaryInspectionLog;
import com.example.mybatisplus.mapper.DormSanitaryInspectionLogMapper;
import com.example.mybatisplus.service.DormSanitaryInspectionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 宿舍卫生检查日志表 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-09 09:20:04
 */
@Service
public class DormSanitaryInspectionLogServiceImpl extends ServiceImpl<DormSanitaryInspectionLogMapper, DormSanitaryInspectionLog> implements DormSanitaryInspectionLogService {

    @Autowired
    private SanitaryInspectionMapper sanitaryInspectionMapper;

    @Override
    public void countScore(Long id) {
        List<DormSanitaryInspectionLog> logs = sanitaryInspectionMapper.countScore(id);

        logs = logs.stream().map(log -> log.setSanitaryInspectionId(id)).collect(Collectors.toList());

        this.saveBatch(logs);
    }
}
