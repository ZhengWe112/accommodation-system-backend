package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.ViolationRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.List;

/**
 * <p>
 * 学生违规记录表 服务类
 * </p>
 *
 * @author team01
 * @since 2024-07-11 07:35:49
 */
public interface ViolationRecordService extends IService<ViolationRecord> {

    List<ViolationRecord> listWithStudent(Wrapper<ViolationRecord> wrapper);
}
