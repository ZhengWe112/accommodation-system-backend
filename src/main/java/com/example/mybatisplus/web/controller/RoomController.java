package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Room;
import com.example.mybatisplus.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 房间表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:26:17
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    /*
    管理员查询所有房间
     */

    @GetMapping("page")
    public JsonResponse Page(Room room, PageDTO pageDTO){
        Page<Room> page = roomService.Page(pageDTO, room);
        return  JsonResponse.success(page);
    }

}

