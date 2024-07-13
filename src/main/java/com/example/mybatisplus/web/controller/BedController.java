package com.example.mybatisplus.web.controller;


import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Bed;
import com.example.mybatisplus.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    /*
    学生查看自己住宿信息
     */
    @GetMapping("selectByNbWithRoom")
    public JsonResponse selectByNbWithRoom(Long number){
        Bed bed = bedService.selectByNbWithRoom(number);
        return  JsonResponse.success(bed);
    }
    /*
    批量入住
     */
    @GetMapping("batchInsert")
    public JsonResponse batchInsert(
            @RequestParam("number") List<Integer> numbers,
            @RequestParam("roomId") List<Integer> roomIds){
        boolean b = bedService.batchInsert(numbers,roomIds);
        return JsonResponse.success(b);
    }
    /*
    批量退宿
     */

    @GetMapping("batchDelete")
    public JsonResponse batchDelete(
            @RequestParam("number") List<Integer> numbers){
        boolean b = bedService.batchDelete(numbers);
        return JsonResponse.success(b);
    }
      /*
    宿管加入学生入住信息
     */
      @GetMapping("insertbyid")
      public JsonResponse insertbyid(Long stuId,Long number){
          boolean b = bedService.insertbyid(stuId,number);
          return JsonResponse.success(b);
      }
    /*
    宿管删除退宿信息
     */

    @GetMapping("deletebyid")
    public JsonResponse deletebyid(Long stuId){
        boolean b = bedService.removeBystu(stuId);
        return JsonResponse.success(b);
    }
    /*
    宿管调换学生
     */

    @GetMapping("updatebyidfree")
    public JsonResponse updatebyidfree(Long stuId,Long number,Long type){
        boolean b;
        if (type==1){
            b = bedService.updateByFree(stuId, number);
        }
        if (type==2) {
            b = bedService.updateByTwo(stuId, number);
        }
        else {
            b=false;
        }
        return JsonResponse.success(b);
    }
}

