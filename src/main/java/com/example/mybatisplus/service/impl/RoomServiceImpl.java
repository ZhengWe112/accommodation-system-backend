package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Room;
import com.example.mybatisplus.mapper.RoomMapper;
import com.example.mybatisplus.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房间表 服务实现类
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:26:17
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

}
