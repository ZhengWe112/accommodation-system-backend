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
 * 卫生异议申请表
 * </p>
 *
 * @author team01
 * @since 2024-07-10 10:11:04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sanitation_objection")
@ApiModel(value = "SanitationObjection对象", description = "卫生异议申请表")
public class SanitationObjection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表student 哪个学生发起的")
    @TableField("student_id")
    private Long studentId;

    @ApiModelProperty("外键 关联到表sanitary_inspection 对哪次检查的异议")
    @TableField("sanitary_inspection_id")
    private Long sanitaryInspectionId;

    @ApiModelProperty("发起的理由")
    @TableField("objection_reason")
    private String objectionReason;

    @ApiModelProperty("发起人的房间号 房间号固定为4为数字 为2位楼栋编号+2位数字")
    @TableField("room_number")
    private String roomNumber;

    @ApiModelProperty("状态 0未送审分管领导 1已送审分管领导 2已经审核完成 发送了反馈")
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
