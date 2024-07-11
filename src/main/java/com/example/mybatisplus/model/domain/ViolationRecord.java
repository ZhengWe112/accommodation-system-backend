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
 * 学生违规记录表
 * </p>
 *
 * @author team01
 * @since 2024-07-11 07:35:49
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("violation_record")
@ApiModel(value = "ViolationRecord对象", description = "学生违规记录表")
public class ViolationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表student 谁违规了")
    @TableField("student_id")
    private Long studentId;

    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private String studentNumber;

    @ApiModelProperty("外键 关联到表dormitory_administrator 谁抓到的")
    @TableField("dormitory_administrator_id")
    private Long dormitoryAdministratorId;

    @ApiModelProperty("被抓的时间")
    @TableField("violation_time")
    private LocalDateTime violationTime;

    @ApiModelProperty("违规的项")
    @TableField("violation_item")
    private String violationItem;

    @ApiModelProperty("定性为违规的理由")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("违规的程度 0记过 1严重警告 2警告")
    @TableField("violation_degree")
    private Integer violationDegree;

    @ApiModelProperty("状态 0未送审分管领导 1分管领导审核中 2分管领导已扣分，已发送警告，完成")
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
