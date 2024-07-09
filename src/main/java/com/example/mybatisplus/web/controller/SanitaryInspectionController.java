package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.DormitoryAdministrator;
import com.example.mybatisplus.model.domain.SanitaryInspection;
import com.example.mybatisplus.service.DormSanitaryInspectionLogService;
import com.example.mybatisplus.service.DormitoryAdministratorService;
import com.example.mybatisplus.service.SanitaryInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private DormitoryAdministratorService dormitoryAdministratorService;

    @Autowired
    private DormSanitaryInspectionLogService dormSanitaryInspectionLogService;

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

        sanitaryInspection.setInspectionTime(LocalDate.now())
                .setDormitoryAdministratorId(id).setState(0).setAverageScore(0f).setOverallSituation(3);

        sanitaryInspectionService.save(sanitaryInspection);
        return JsonResponse.success("创建成功");
    }

    @DeleteMapping("{id}")
    public JsonResponse<String> deleteById(@PathVariable Long id) {
        sanitaryInspectionService.removeById(id);
        return JsonResponse.success("删除成功");
    }

    @GetMapping("/submit")
    public JsonResponse<String> submit(Long id) {
        SanitaryInspection sanitaryInspection = sanitaryInspectionService.getById(id);
        sanitaryInspection.setState(1); // state == 1表示已送审
        sanitaryInspectionService.updateById(sanitaryInspection);
        return JsonResponse.success("送审成功");
    }

    @GetMapping("/submitted")
    public JsonResponse<List<SanitaryInspection>> getSubmitted() {
        LambdaQueryWrapper<SanitaryInspection> wrapper = new LambdaQueryWrapper<>();

        // state = 1表示已送审
        wrapper.in(SanitaryInspection::getState, 1, 2);
        List<SanitaryInspection> sanitaryInspections = sanitaryInspectionService.list(wrapper);

        sanitaryInspections= sanitaryInspections.stream().peek(item -> {
            DormitoryAdministrator dormitoryAdministrator = dormitoryAdministratorService.getById(item.getDormitoryAdministratorId());
            item.setDormitoryAdministratorName(dormitoryAdministrator.getFullname());
        }).collect(Collectors.toList());

        return JsonResponse.success(sanitaryInspections);
    }

    @GetMapping("/pub")
    public JsonResponse<String> pub(Long id) {
        SanitaryInspection sanitaryInspection = new SanitaryInspection();
        sanitaryInspection.setId(id).setState(2);
        sanitaryInspectionService.updateById(sanitaryInspection);

        dormSanitaryInspectionLogService.countScore(id);
        return JsonResponse.success("发布成功");
    }
}

