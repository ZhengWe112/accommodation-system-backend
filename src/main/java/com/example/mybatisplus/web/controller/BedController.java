package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Bed;
import com.example.mybatisplus.model.domain.Room;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.domain.Teacher;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.BedService;
import com.example.mybatisplus.service.RoomService;
import com.example.mybatisplus.service.StudentService;
import com.example.mybatisplus.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 床位表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-13 12:23:18
 */
@RestController
@RequestMapping("/api/bed")
public class BedController {

    @Autowired
    private BedService bedService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RoomService roomService;

    @GetMapping("list")
    public JsonResponse list(int pageNo, int pageSize) {
        Page<Bed> page = bedService.page(new Page<>(pageNo, pageSize));
        List<Bed> records = page.getRecords();
        records = records.stream().map(record -> {
            if (record.getStudentId() != null) {
                Student student = studentService.getById(record.getStudentId());
                record.setStudentName(student.getFullname());
            } else if (record.getTeacherId() != null) {
                Teacher teacher = teacherService.getById(record.getTeacherId());
                record.setTeacherName(teacher.getFullname());
            }

            Room room = roomService.getById(record.getRoomId());
            return record.setRoomNumber(room.getRoomNumber());
        }).collect(Collectors.toList());

        return JsonResponse.success(new PageResponseDTO<>(records, page.getTotal()));
    }

}

