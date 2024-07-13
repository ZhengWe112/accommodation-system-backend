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
 * 床位表
 * </p>
 *
 * @author team01
 * @since 2024-07-13 12:23:18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("bed")
@ApiModel(value = "Bed对象", description = "床位表")
public class Bed implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表student 这张床是谁的")
    @TableField("student_id")
    private Long studentId;

    @ApiModelProperty("外键 关联到表teacher 这张床是谁的")
    @TableField("teacher_id")
    private Long teacherId;

    @ApiModelProperty("外键 关联到表room 这张床在哪个房间")
    @TableField("room_id")
    private Long roomId;

    @ApiModelProperty("床位号")
    @TableField("bed_number")
    private Integer bedNumber;

    @ApiModelProperty("此床是否有人 0没有 1有 这个字段为1时student_id，teacher_id才有意义")
    @TableField("is_empty")
    private Boolean isEmpty;

    @ApiModelProperty("0表示是老师的床 1表示是学生的床 这个字段决定了student_id，teacher_id谁有意义")
    @TableField("type")
    private Boolean type;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
