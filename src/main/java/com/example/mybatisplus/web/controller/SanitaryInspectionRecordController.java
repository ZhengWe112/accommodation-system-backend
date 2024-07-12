package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Room;
import com.example.mybatisplus.model.domain.SanitaryInspectionRecord;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.DormSanitaryInspectionLogService;
import com.example.mybatisplus.service.RoomService;
import com.example.mybatisplus.service.SanitaryInspectionRecordService;
import com.example.mybatisplus.service.SanitaryInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 卫生检查明细表 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-08 07:22:02
 */
@RestController
@RequestMapping("/api/sanitaryInspectionRecord")
public class SanitaryInspectionRecordController {

    @Autowired
    private SanitaryInspectionRecordService sanitaryInspectionRecordService;

    @Autowired
    private SanitaryInspectionService sanitaryInspectionService;

    @Autowired
    private DormSanitaryInspectionLogService dormSanitaryInspectionLogService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public JsonResponse listBySanitaryInspectionId(Long id, int pageNo, int pageSize) {
        LambdaQueryWrapper<SanitaryInspectionRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SanitaryInspectionRecord::getSanitaryInspectionId, id);

        Page<SanitaryInspectionRecord> pageInfo = new Page<>(pageNo, pageSize);

        Page<SanitaryInspectionRecord> recordPage = sanitaryInspectionRecordService.page(pageInfo, wrapper);

        List<SanitaryInspectionRecord> records = recordPage.getRecords();

        records = records.stream().map(record -> {
            Room room = roomService.getById(record.getRoomId());
            return record.setRoomNumber(room.getRoomNumber());
        }).collect(Collectors.toList());
        return JsonResponse.success(new PageResponseDTO<>(records, recordPage.getTotal()));
    }

    @PostMapping("/update")
    public JsonResponse<String> updateOne(@RequestBody SanitaryInspectionRecord record) {
        sanitaryInspectionRecordService.updateById(record);

        // 修改分数后 总分也要修改
        sanitaryInspectionService.updateScore(record.getSanitaryInspectionId());
        dormSanitaryInspectionLogService.updateScore(record.getSanitaryInspectionId());
        return JsonResponse.success("修改成功");
    }

    @PostMapping("/add")
    public JsonResponse<String> addOne(@RequestBody SanitaryInspectionRecord record) {
        sanitaryInspectionRecordService.save(record);
        sanitaryInspectionService.updateScore(record.getSanitaryInspectionId());
        dormSanitaryInspectionLogService.updateScore(record.getSanitaryInspectionId());
        return JsonResponse.success("添加成功");
    }

    @GetMapping("/delete")
    public JsonResponse<String> deleteById(Long id, Long sanitaryInspectionId) {
        sanitaryInspectionRecordService.removeById(id);
        sanitaryInspectionService.updateScore(sanitaryInspectionId);
        dormSanitaryInspectionLogService.updateScore(sanitaryInspectionId);
        return JsonResponse.success("删除成功");
    }
}

