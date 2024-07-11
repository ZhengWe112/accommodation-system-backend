package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 房间表 Mapper 接口
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:26:17
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {

}
