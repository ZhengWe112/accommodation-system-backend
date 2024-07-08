package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.SanitaryInspection;
import com.example.mybatisplus.mapper.SanitaryInspectionMapper;
import com.example.mybatisplus.service.SanitaryInspectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
