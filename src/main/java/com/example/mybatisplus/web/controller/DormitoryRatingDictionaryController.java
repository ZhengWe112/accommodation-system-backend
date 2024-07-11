package com.example.mybatisplus.web.controller;


import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.DormitoryRatingDictionary;
import com.example.mybatisplus.service.DormitoryRatingDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 寝室评分字典 怎么去计算寝室的得分 前端控制器
 * </p>
 *
 * @author team01
 * @since 2024-07-10 01:53:12
 */
@RestController
@RequestMapping("/api/dormitoryRatingDictionary")
public class DormitoryRatingDictionaryController {

    @Autowired
    DormitoryRatingDictionaryService dormitoryRatingDictionaryService;

    @GetMapping("/list")
    public JsonResponse<List<DormitoryRatingDictionary>> list() {
        return JsonResponse.success(dormitoryRatingDictionaryService.list());
    }

}

