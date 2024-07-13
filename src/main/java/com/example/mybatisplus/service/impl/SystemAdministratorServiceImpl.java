package com.example.mybatisplus.service.impl;


import com.example.mybatisplus.model.domain.SystemAdministrator;
import com.example.mybatisplus.mapper.SystemAdministratorMapper;
import com.example.mybatisplus.service.SystemAdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SystemAdministratorServiceImpl extends ServiceImpl<SystemAdministratorMapper, SystemAdministrator> implements SystemAdministratorService {
}

