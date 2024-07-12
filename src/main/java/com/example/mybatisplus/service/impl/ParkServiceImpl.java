package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Park;
import com.example.mybatisplus.mapper.ParkMapper;
import com.example.mybatisplus.service.ParkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 园区表 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-12 05:37:56
 */
@Service
public class ParkServiceImpl extends ServiceImpl<ParkMapper, Park> implements ParkService {

}
