package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Bed;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 床位表 服务类
 * </p>
 *
 * @author team01
 * @since 2024-07-13 12:23:18
 */
public interface BedService extends IService<Bed> {

    Bed selectByNbWithRoom(Long number);

    boolean batchInsert(List<Integer> numbers, List<Integer> roomIds);

    boolean batchDelete(List<Integer> numbers);

    boolean removeBystu(Long stuId);

    boolean updateByFree(Long stuId, Long number);

    boolean updateByTwo(Long stuId, Long stuId1);

    boolean insertbyid(Long stuId, Long number);
}
