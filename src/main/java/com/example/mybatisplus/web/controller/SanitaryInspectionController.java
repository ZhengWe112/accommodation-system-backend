package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.SanitaryInspection;
import com.example.mybatisplus.service.SanitaryInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

/**
 * <p>
 * 卫生检查表 此表只是记录了一次卫生检查的整体情况 明细情况在表sanitary_inspection_record 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-08 01:46:28
 */
@RestController
@RequestMapping("/api/sanitaryInspection")
public class SanitaryInspectionController {

    @Autowired
    private SanitaryInspectionService sanitaryInspectionService;

    @GetMapping
    public JsonResponse<List<SanitaryInspection>> list() {
        return JsonResponse.success(sanitaryInspectionService.list());
    }

    @GetMapping("{id}")
    public JsonResponse<List<SanitaryInspection>> getByDormAdminId(@PathVariable Long id) {
        LambdaQueryWrapper<SanitaryInspection> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SanitaryInspection::getDormitoryAdministratorId, id);
        return JsonResponse.success(sanitaryInspectionService.list(wrapper));
    }

    @PutMapping("{id}")
    public JsonResponse<String> add(@PathVariable Long id) {
        SanitaryInspection sanitaryInspection = new SanitaryInspection();

        // sanitaryInspection.setInspectionTime()
        // sanitaryInspectionService.save()
        return null;
    }
}

