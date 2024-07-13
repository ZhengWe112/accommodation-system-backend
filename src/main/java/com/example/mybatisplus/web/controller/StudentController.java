package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.model.dto.PageResponseDTO;
import com.example.mybatisplus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BedService bedService;


    // 需求：表单填写完成并提交后，申请状态会变为“正在审核”
    @PostMapping("/accommodationApplication")
    public JsonResponse<String> apply(@RequestBody AccommodationApplication accommodationApplication) {
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
    public JsonResponse listAccommodationApplication(@PathVariable Long id,
                             @RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "10") int pageSize){
        // 分页查找方法，获取给定id学生的所有申请
        LambdaQueryWrapper<AccommodationApplication> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AccommodationApplication::getStudentId, id);
        Page<AccommodationApplication> pageInfo = new Page<>(pageNo, pageSize);
        Page<AccommodationApplication> page = accommodationApplicationService.page(pageInfo, wrapper);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }

    // 需求：学生登记维修信息，提出维修申请，维修管理员可以收到维修通知。
    @PostMapping("/maintenance")
    public JsonResponse<String> request(@RequestBody MaintenanceRequest maintenanceRequest) {
        boolean addedFlag = maintenanceRequestService.save(maintenanceRequest); // save方法添加维修申请
        if (addedFlag) {
            return JsonResponse.success("维修申请成功。");
        } else {
            return JsonResponse.failure("维修申请失败。");
        }
    }

    @GetMapping("/maintenance/{id}")
    public JsonResponse listMaintenance(@PathVariable Long id,
                             @RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "10") int pageSize){
        // 分页查找方法，获取给定id学生的所有申请
        LambdaQueryWrapper<MaintenanceRequest> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MaintenanceRequest::getStudentId, id);
        Page<MaintenanceRequest> pageInfo = new Page<>(pageNo, pageSize);
        Page<MaintenanceRequest> page = maintenanceRequestService.page(pageInfo, wrapper);

        return JsonResponse.success(new PageResponseDTO<>(page.getRecords(), page.getTotal()));
    }

    @GetMapping("/list/unbooked")
    public JsonResponse listUnbooked() {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Student::getIsDormitoryResident, 0);

        List<Student> students = studentService.list(wrapper);

        return JsonResponse.success(students);
    }

    @PostMapping("/batch")
    public JsonResponse<String> batch(@RequestBody List<Student> students) {
        students = students.stream().filter(student -> student.getBedId() == null).collect(Collectors.toList());

        List<Bed> beds = bedService.list(new LambdaQueryWrapper<Bed>().eq(Bed::getIsEmpty, 0));
        if (beds.size() < students.size()) {
            return JsonResponse.failure("床位不够了");
        } else {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                Bed bed = beds.get(i);
                student.setBedId(bed.getId());
                bed.setStudentId(student.getId());
            }

            bedService.updateBatchById(beds);
            studentService.updateBatchById(students);

        }
        return JsonResponse.success("success");
    }

    @PostMapping("/unBatch")
    public JsonResponse<String> unBatch(@RequestBody List<Student> students) {
        students = students.stream().filter(student -> student.getBedId() != null).collect(Collectors.toList());

        List<Long> ids = students.stream().map(Student::getBedId).toList();

        List<Bed> beds = ids.stream().map(id -> bedService.getById(id)).toList();

        beds = beds.stream().map(bed -> bed.setStudentId(null)).collect(Collectors.toList());

        bedService.updateBatchById(beds);

        students = students.stream().map(student -> student.setBedId(null)).collect(Collectors.toList());

        studentService.updateBatchById(students);

        return JsonResponse.success("success");
    }

    @GetMapping("list")
    @ResponseBody
    public JsonResponse list(){
        List<Student> list = studentService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("/listByKey")
    @ResponseBody
    public JsonResponse listByKey(@RequestBody Student student){
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Student::getStudentId,student.getStudentId());
        List<Student> list = studentService.list(wrapper);
        return JsonResponse.success(list);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public JsonResponse updateById(@RequestBody Student student ){
        boolean b = studentService.updateById(student);
        return JsonResponse.success(b);
    }

    @PostMapping("/insertById")
    @ResponseBody
    public JsonResponse insertById(@RequestBody Student student ){
        boolean save = studentService.save(student);
        return JsonResponse.success(save);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody Student student ){
        boolean b = studentService.removeById(student);
        return JsonResponse.success(b);
    }
}

