package com.example.mybatisplus;

import com.example.mybatisplus.mapper.SanitaryInspectionMapper;
import com.example.mybatisplus.model.domain.DormSanitaryInspectionLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AccommodationSystemTests {

    @Autowired
    SanitaryInspectionMapper sanitaryInspectionMapper;

    @Test
    void contextLoads() {
        List<DormSanitaryInspectionLog> logs = sanitaryInspectionMapper.countScore(1L);
        System.out.println(logs);
    }

}
