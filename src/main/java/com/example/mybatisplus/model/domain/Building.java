package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 楼栋表
 * </p>
 *
 * @author team01
 * @since 2024-07-12 11:24:54
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("building")
@ApiModel(value = "Building对象", description = "楼栋表")
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表dormitory_administrator 谁是这栋楼的宿管")
    @TableField("dormitory_administrator_id")
    private Long dormitoryAdministratorId;

    @ApiModelProperty("外键 关联到表park 这栋楼在那个园区")
    @TableField("park_id")
    private Long parkId;

    @ApiModelProperty("楼栋名")
    @TableField("building_name")
    private String buildingName;

    @ApiModelProperty("楼栋号 楼栋号用且必须用两位数字表示 如01 02")
    @TableField("building_number")
    private String buildingNumber;

    @ApiModelProperty("楼层数")
    @TableField("nb_floor")
    private Integer nbFloor;

    @ApiModelProperty("竣工时间")
    @TableField("complicated_time")
    private LocalDate complicatedTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
