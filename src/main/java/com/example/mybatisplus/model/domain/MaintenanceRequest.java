package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 维修申请表 由学生发起的
 * </p>
 *
 * @author team01
 * @since 2024-07-13 04:34:27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("maintenance_request")
@ApiModel(value = "MaintenanceRequest对象", description = "维修申请表 由学生发起的")
public class MaintenanceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表student 这次维修是哪个学生发起的")
    @TableField("student_id")
    private Long studentId;

    @ApiModelProperty("发起维修请求的时间")
    @TableField("request_time")
    private LocalDateTime requestTime;

    @ApiModelProperty("请求维修的东西")
    @TableField("request_item")
    private String requestItem;

    @ApiModelProperty("怎么坏了")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("学生需要填写所在寝室号 寝室号固定为4为数字 为2位楼栋编号+2位数字")
    @TableField("room_number")
    private String roomNumber;

    @ApiModelProperty("状态 0表示申请中 1表示已维修")
    @TableField("state")
    private Integer state;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;


}
