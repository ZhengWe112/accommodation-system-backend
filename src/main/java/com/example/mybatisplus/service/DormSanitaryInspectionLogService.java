package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.DormSanitaryInspectionLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 宿舍卫生检查日志表 服务类
 * </p>
 *
 * @author team01
 * @since 2024-07-09 09:20:04
 */
public interface DormSanitaryInspectionLogService extends IService<DormSanitaryInspectionLog> {

    void countScore(Long id);
}
