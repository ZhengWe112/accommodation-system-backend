package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.AccommodationApplication;
import com.example.mybatisplus.model.domain.AccommodationLog;
import com.example.mybatisplus.model.domain.Park;
import com.example.mybatisplus.model.domain.SanitationObjectionReviewResultNotification;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.AccommodationApplicationService;
import com.example.mybatisplus.service.AccommodationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:30
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {
    // 对于下列五个需求：
    // 普通入住申请：学生填写入住理由，并提交至分管领导审核。
    // 普通调宿申请：学生填写调整理由，并提交至分管领导审核，审核通过之后移交至对应的宿舍管理员。
    // 互换申请：学生填写理由，并提交至分管领导审核，审核通过之后移交至对应的宿舍管理员。注：互换指两个学生互换床位，调宿指一个学生调整到一个空床位。
    // 个人退宿申请：学生在线申请退宿，并提交至分管领导审核，审核通过之后移交至对应的宿舍管理员进行退宿处理。
    // 校外住宿申请：学生在线申请校外住宿，提交至分管领导审核，审核通过之后移交至对应宿舍管理员进行退宿处理。
    // 结合AccommodationApplicationService数据库设计，可以统一到一个申请函数当中

    @Autowired
    private AccommodationApplicationService accommodationApplicationService;

    @Autowired
    private AccommodationLogService accommodationLogService;

    @PostMapping("/accommodationApplication")
    public JsonResponse<String> accommodationApplication(@RequestBody AccommodationApplication accommodationApplication) {
        // 需求：表单填写完成并提交后，申请状态会变为“正在审核”
        // 对应到表中state:申请状态 0表示等待审核 1表示审核通过 2表示被驳回
//        accommodationApplication.setState(0);// 应该在前端封装
        boolean addedFlag = accommodationApplicationService.save(accommodationApplication); // save方法添加住退宿申请
        if (addedFlag) {
            return JsonResponse.success("住退宿申请成功。");
        } else {
            return JsonResponse.failure("住退宿申请失败。");
        }
    }

    @GetMapping("/accommodationApplication/{id}")
    public JsonResponse list(@PathVariable Long id,
                             @RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "10") int pageSize){
        // 分页查找方法，获取给定id学生的所有申请
        LambdaQueryWrapper<AccommodationLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AccommodationLog::getStudentId, id);
        Page<AccommodationLog> pageInfo = new Page<>(pageNo, pageSize);
        Page<AccommodationLog> page = accommodationLogService.page(pageInfo, wrapper);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }
}

