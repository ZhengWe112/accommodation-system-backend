package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 卫生检查表 此表只是记录了一次卫生检查的整体情况 明细情况在表sanitary_inspection_record
 * </p>
 *
 * @author team01
 * @since 2024-07-08 01:46:28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sanitary_inspection")
@ApiModel(value = "SanitaryInspection对象", description = "卫生检查表 此表只是记录了一次卫生检查的整体情况 明细情况在表sanitary_inspection_record")
public class SanitaryInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表dormitory_administrator 谁创建的卫生检查")
    @TableField("dormitory_administrator_id")
    private Long dormitoryAdministratorId;

    @TableField(exist = false)
    private String dormitoryAdministratorName;

    @ApiModelProperty("检查时间")
    @TableField("inspection_time")
    private LocalDate inspectionTime;

    @ApiModelProperty("整体情况 0优 1良 2中 3差")
    @TableField("overall_situation")
    private Integer overallSituation;

    @ApiModelProperty("所有寝室的平均分")
    @TableField("average_score")
    private Float averageScore;

    @ApiModelProperty("状态 0未送审分管领导 1已送审分管领导 2已公布成绩")
    @TableField("state")
    private Integer state;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
