package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.mapper.ParkMapper;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统管理员 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:17:30
 */
@RestController
@RequestMapping("/api/systemAdministrator")
public class SystemAdministratorController {

    @Autowired
    private ParkService parkService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private SystemAdministratorService systemAdministratorService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BedService bedService;

    // 园区管理：添加、编辑、删除园区信息，包括园区名称、类型（教师/学生）、地址等。
    @PostMapping("/parkManagement/insert") // 添加园区
    public JsonResponse<String> insert(@RequestBody Park park) {
        boolean addedFlag = parkService.save(park); // save方法添加园区信息
        if (addedFlag) {
            return JsonResponse.success("园区添加成功。");
        } else {
            return JsonResponse.failure("园区添加失败。");
        }
    }

    @PostMapping("/parkManagement/update") // 更新园区
    public JsonResponse<String> update(@RequestBody Park park) {
            boolean updatedFlag = parkService.updateById(park);
            if (updatedFlag) {
                return JsonResponse.success("园区更新成功。");
            } else {
                return JsonResponse.failure("园区更新失败。");
            }
    }

    @DeleteMapping("/parkManagement/delete/{id}") // 删除园区
    public JsonResponse<String> deleteByParkId(@PathVariable Long id) {
        boolean deletedFlag = parkService.removeById(id);
        if (deletedFlag) {
            return JsonResponse.success("园区删除成功。");
        } else {
            return JsonResponse.failure("园区删除失败。");
        }
    }

    // 楼栋管理：在园区下添加、编辑、删除楼栋信息，包括楼栋编号、楼层数等。
    @PostMapping("/buildingManagement/insert") // 在园区下添加楼栋
    public JsonResponse<String> insert(@RequestBody Building building) {
        boolean addedFlag = buildingService.save(building);
        if (addedFlag) {
            return JsonResponse.success("楼栋添加成功。");
        } else {
            return JsonResponse.failure("楼栋添加失败。");
        }
    }

    @PostMapping("/buildingManagement/update") // 编辑楼栋
    public JsonResponse<String> update(@RequestBody Building building) {
        boolean updatedFlag = buildingService.updateById(building);
        if (updatedFlag) {
            return JsonResponse.success("楼栋更新成功。");
        } else {
            return JsonResponse.failure("楼栋更新失败。");
        }
    }

    @DeleteMapping("/buildingManagement/delete/{id}") // 删除楼栋
    public JsonResponse<String> deleteByBuildingId(@PathVariable Long id) {
        boolean deletedFlag = buildingService.removeById(id);
        if (deletedFlag) {
            return JsonResponse.success("楼栋删除成功。");
        } else {
            return JsonResponse.failure("楼栋删除失败。");
        }
    }

    // 房间管理：在楼栋下添加、编辑、删除房间信息，包括房间编号、类型（公寓房、辅导员、门卫室等）、面积等。
    @PostMapping("/roomManagement/insert") // 在楼栋下添加房间
    public JsonResponse<String> insert(@RequestBody Room room) {
        boolean addedFlag = roomService.save(room);
        if (addedFlag) {
            return JsonResponse.success("房间添加成功。");
        } else {
            return JsonResponse.failure("房间添加失败。");
        }
    }

    @PostMapping("/roomManagement/update") // 编辑房间
    public JsonResponse<String> update(@RequestBody Room room) {
        boolean updatedFlag = roomService.updateById(room);
        if (updatedFlag) {
            return JsonResponse.success("房间更新成功。");
        } else {
            return JsonResponse.failure("房间更新失败。");
        }
    }

    @DeleteMapping("/roomManagement/delete/{id}") // 删除房间
    public JsonResponse<String> deleteByRoomId(@PathVariable Long id) {
        boolean deletedFlag = roomService.removeById(id);
        if (deletedFlag) {
            return JsonResponse.success("房间删除成功。");
        } else {
            return JsonResponse.failure("房间删除失败。");
        }
    }

    // 床位管理：在房间下添加、编辑、删除床位信息，包括床位编号。
    @PostMapping("/bedManagement/insert") // 在房间下添加床位
    public JsonResponse<String> insert(@RequestBody Bed bed) {
        boolean addedFlag = bedService.save(bed);
        if (addedFlag) {
            return JsonResponse.success("床位添加成功。");
        } else {
            return JsonResponse.failure("床位添加失败。");
        }
    }

    @PostMapping("/bedManagement/update") // 编辑床位
    public JsonResponse<String> update(@RequestBody Bed bed) {
        boolean updatedFlag = bedService.updateById(bed);
        if (updatedFlag) {
            return JsonResponse.success("床位更新成功。");
        } else {
            return JsonResponse.failure("床位更新失败。");
        }
    }

    @DeleteMapping("/bedManagement/delete/{id}") // 删除床位
    public JsonResponse<String> deleteByBedId(@PathVariable Long id) {
        boolean deletedFlag = bedService.removeById(id);
        if (deletedFlag) {
            return JsonResponse.success("床位删除成功。");
        } else {
            return JsonResponse.failure("床位删除失败。");
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public JsonResponse list(){
        List<SystemAdministrator> list =  systemAdministratorService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("/listByKey")
    @ResponseBody
    public JsonResponse listByKey(@RequestBody SystemAdministrator     systemAdministrator){
        LambdaQueryWrapper<SystemAdministrator> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(SystemAdministrator::getJobId,     systemAdministrator.getJobId());
        List<SystemAdministrator> list = systemAdministratorService.list(wrapper);
        return JsonResponse.success(list);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public JsonResponse updateById(@RequestBody SystemAdministrator  systemAdministrator ){
        boolean b = systemAdministratorService.updateById(  systemAdministrator);
        return JsonResponse.success(b);
    }

    @PostMapping("/insertById")
    @ResponseBody
    public JsonResponse insertById(@RequestBody SystemAdministrator     systemAdministrator ){
        boolean save =     systemAdministratorService.save(    systemAdministrator);
        return JsonResponse.success(save);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public JsonResponse deleteById(@RequestBody SystemAdministrator     systemAdministrator ){
        boolean b =     systemAdministratorService.removeById(    systemAdministrator);
        return JsonResponse.success(b);
    }

}

