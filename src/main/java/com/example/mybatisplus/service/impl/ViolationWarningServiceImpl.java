package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.ViolationWarning;
import com.example.mybatisplus.mapper.ViolationWarningMapper;
import com.example.mybatisplus.service.ViolationWarningService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生违规警告表 这是分管领导发给学生的 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-11 09:44:01
 */
@Service
public class ViolationWarningServiceImpl extends ServiceImpl<ViolationWarningMapper, ViolationWarning> implements ViolationWarningService {

}
