package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.model.dto.LoginInfoDTO;
import com.example.mybatisplus.model.dto.UserInfoDTO;
import com.example.mybatisplus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private SystemAdministratorService systemAdministratorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MaintenanceAdministratorService maintenanceAdministratorService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DormitoryAdministratorService dormitoryAdministratorService;

    @Autowired
    private ResponsibleLeaderService responsibleLeaderService;

    @PostMapping
    public JsonResponse<UserInfoDTO> login(@RequestBody LoginInfoDTO info) {
        switch (info.getRole()) {
            case 1 -> {
                LambdaQueryWrapper<SystemAdministrator> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(SystemAdministrator::getJobId, info.getNumber())
                        .eq(SystemAdministrator::getPassword, info.getPassword());
                SystemAdministrator systemAdministrator = systemAdministratorService.getOne(wrapper);
                if (systemAdministrator != null) {
                    UserInfoDTO userInfoDTO = new UserInfoDTO(systemAdministrator.getId(), systemAdministrator.getFullname(), systemAdministrator.getRoleId());
                    SessionUtils.saveCurrentUserInfo(userInfoDTO);
                    return JsonResponse.success(userInfoDTO);
                }
            }
            case 2 -> {
                LambdaQueryWrapper<MaintenanceAdministrator> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(MaintenanceAdministrator::getJobId, info.getNumber()).eq(MaintenanceAdministrator::getPassword, info.getPassword());
                MaintenanceAdministrator maintenanceAdministrator = maintenanceAdministratorService.getOne(wrapper);
                if (maintenanceAdministrator != null) {
                    UserInfoDTO userInfoDTO = new UserInfoDTO(maintenanceAdministrator.getId(), maintenanceAdministrator.getFullname(), maintenanceAdministrator.getRoleId());
                    SessionUtils.saveCurrentUserInfo(userInfoDTO);
                    return JsonResponse.success(userInfoDTO);
                }
            }
            case 3 -> {
                LambdaQueryWrapper<DormitoryAdministrator> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(DormitoryAdministrator::getJobId, info.getNumber()).eq(DormitoryAdministrator::getPassword, info.getPassword());
                DormitoryAdministrator dormitoryAdministrator = dormitoryAdministratorService.getOne(wrapper);
                if (dormitoryAdministrator != null) {
                    UserInfoDTO userInfoDTO = new UserInfoDTO(dormitoryAdministrator.getId(), dormitoryAdministrator.getFullname(), dormitoryAdministrator.getRoleId());
                    SessionUtils.saveCurrentUserInfo(userInfoDTO);
                    return JsonResponse.success(userInfoDTO);
                }
            }
            case 4 -> {
                LambdaQueryWrapper<ResponsibleLeader> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(ResponsibleLeader::getJobId, info.getNumber()).eq(ResponsibleLeader::getPassword, info.getPassword());
                ResponsibleLeader responsibleLeader = responsibleLeaderService.getOne(wrapper);
                if (responsibleLeader != null) {
                    UserInfoDTO userInfoDTO = new UserInfoDTO(responsibleLeader.getId(), responsibleLeader.getFullname(), responsibleLeader.getRoleId());
                    SessionUtils.saveCurrentUserInfo(userInfoDTO);
                    return JsonResponse.success(userInfoDTO);
                }
            }
            case 5 -> {
                LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Teacher::getTeacherId, info.getNumber()).eq(Teacher::getPassword, info.getPassword());
                Teacher teacher = teacherService.getOne(wrapper);
                if (teacher != null) {
                    UserInfoDTO userInfoDTO = new UserInfoDTO(teacher.getId(), teacher.getFullname(), teacher.getRoleId());
                    SessionUtils.saveCurrentUserInfo(userInfoDTO);
                    return JsonResponse.success(userInfoDTO);
                }
            }
            case 6 -> {
                LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Student::getFullname, info.getNumber()).eq(Student::getPassword, info.getPassword());
                Student student = studentService.getOne(wrapper);
                if (student != null) {
                    UserInfoDTO userInfoDTO = new UserInfoDTO(student.getId(), student.getFullname(), student.getRoleId());
                    SessionUtils.saveCurrentUserInfo(userInfoDTO);
                    return JsonResponse.success(userInfoDTO);
                }
            }
        }

        return JsonResponse.failure("用户名或密码错误！");
    }
}
