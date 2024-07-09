package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.SanitaryInspection;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 卫生检查表 此表只是记录了一次卫生检查的整体情况 明细情况在表sanitary_inspection_record 服务类
 * </p>
 *
 * @author team01
 * @since 2024-07-08 01:46:28
 */
public interface SanitaryInspectionService extends IService<SanitaryInspection> {

    void updateScore(Long sanitaryInspectionId);
}
