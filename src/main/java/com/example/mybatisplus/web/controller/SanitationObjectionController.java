package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Room;
import com.example.mybatisplus.model.domain.SanitationObjection;
import com.example.mybatisplus.service.RoomService;
import com.example.mybatisplus.service.SanitationObjectionReviewResultNotificationService;
import com.example.mybatisplus.service.SanitationObjectionService;
import com.example.mybatisplus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 卫生异议申请表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-10 10:11:04
 */
@RestController
@RequestMapping("/api/sanitationObjection")
public class SanitationObjectionController {

    @Autowired
    private SanitationObjectionService sanitationObjectionService;

    @Autowired
    private SanitationObjectionReviewResultNotificationService sanitationObjectionReviewResultNotificationService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public JsonResponse<List<SanitationObjection>> list(Integer state, Long studentId) {
        LambdaQueryWrapper<SanitationObjection> wrapper = new LambdaQueryWrapper<>();
        if (state != null) {
            wrapper.ge(SanitationObjection::getState, state);
        }

        if (studentId != null) {
            wrapper.eq(SanitationObjection::getStudentId, studentId);
        }

        List<SanitationObjection> objections = sanitationObjectionService.list(wrapper);
        return JsonResponse.success(objections);
    }

    @PostMapping("/add")
    public JsonResponse<String> add(@RequestBody SanitationObjection sanitationObjection) {
        Long roomId = studentService.getRoomId(sanitationObjection.getStudentId());

        Room room = roomService.getById(roomId);

        sanitationObjection.setRoomNumber(room.getRoomNumber());

        sanitationObjectionService.save(sanitationObjection);
        return JsonResponse.success("添加成功");
    }

    @GetMapping("/apply")
    public JsonResponse<String> apply(Long id) {
        SanitationObjection sanitationObjection = new SanitationObjection();
        sanitationObjection.setId(id).setState(1);
        sanitationObjectionService.updateById(sanitationObjection);
        return JsonResponse.success("success");
    }

    @GetMapping("/submit")
    public JsonResponse<String> submit(Long id) {
        SanitationObjection sanitationObjection = new SanitationObjection();
        sanitationObjection.setId(id).setState(2);
        sanitationObjectionService.updateById(sanitationObjection);
        return JsonResponse.success("success");
    }

    @GetMapping("/del")
    public JsonResponse<String> del(Long id) {
        sanitationObjectionService.removeById(id);
        return JsonResponse.success("success");
    }

    @GetMapping("/agree")
    public JsonResponse<String> agree(Long id, Integer isAgree) {
        SanitationObjection sanitationObjection = new SanitationObjection();
        sanitationObjection.setId(id);
        if (isAgree == 0) {
            sanitationObjection.setState(3);
        } else {
            sanitationObjection.setState(4);
        }
        sanitationObjectionService.updateById(sanitationObjection);

        sanitationObjection = sanitationObjectionService.getById(sanitationObjection.getId());

        sanitationObjectionReviewResultNotificationService.sendNotification(sanitationObjection);

        return JsonResponse.success("success");
    }
}

