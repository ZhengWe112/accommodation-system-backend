package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Building;
import com.example.mybatisplus.mapper.BuildingMapper;
import com.example.mybatisplus.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 楼栋表 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-12 11:24:54
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {

}
