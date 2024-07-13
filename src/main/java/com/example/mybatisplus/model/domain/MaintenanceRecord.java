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
 * 维修记录表 记录了所有维修的历史
 * </p>
 *
 * @author team01
 * @since 2024-07-13 04:34:16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("maintenance_record")
@ApiModel(value = "MaintenanceRecord对象", description = "维修记录表 记录了所有维修的历史")
public class MaintenanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表student 这次维修是哪个学生发起的")
    @TableField("student_id")
    private Long studentId;

    @ApiModelProperty("被维修的东西")
    @TableField("maintenance_item")
    private String maintenanceItem;

    @ApiModelProperty("维修时间")
    @TableField("maintenance_time")
    private LocalDate maintenanceTime;

    @ApiModelProperty("损坏的原因")
    @TableField("damage_reason")
    private String damageReason;

    @ApiModelProperty("修理人员的名字 为了简化 我们把修理人员当作是系统外部的角色")
    @TableField("maintainer_name")
    private String maintainerName;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
