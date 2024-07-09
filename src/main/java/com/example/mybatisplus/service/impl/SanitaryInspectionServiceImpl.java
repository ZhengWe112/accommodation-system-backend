package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.DormSanitaryInspectionLog;
import com.example.mybatisplus.model.domain.SanitaryInspection;
import com.example.mybatisplus.mapper.SanitaryInspectionMapper;
import com.example.mybatisplus.service.SanitaryInspectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 卫生检查表 此表只是记录了一次卫生检查的整体情况 明细情况在表sanitary_inspection_record 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-08 01:46:28
 */
@Service
public class SanitaryInspectionServiceImpl extends ServiceImpl<SanitaryInspectionMapper, SanitaryInspection> implements SanitaryInspectionService {

    @Autowired
    SanitaryInspectionMapper sanitaryInspectionMapper;

    @Override
    public void updateScore(Long sanitaryInspectionId) {
        List<DormSanitaryInspectionLog> logs = sanitaryInspectionMapper.countScore(sanitaryInspectionId);

        // 计算平均分
        float average = logs.stream().map(DormSanitaryInspectionLog::getScore).reduce(0f, Float::sum);
        SanitaryInspection sanitaryInspection = new SanitaryInspection();

        if (average >= 85) {
            sanitaryInspection.setOverallSituation(0); // 优
        } else if (average >= 75) {
            sanitaryInspection.setOverallSituation(1); // 良
        } else if (average >= 60) {
            sanitaryInspection.setOverallSituation(2); // 中
        } else {
            sanitaryInspection.setOverallSituation(3); // 差
        }

        sanitaryInspection.setId(sanitaryInspectionId).setAverageScore(average);
        sanitaryInspectionMapper.updateById(sanitaryInspection);
    }
}
