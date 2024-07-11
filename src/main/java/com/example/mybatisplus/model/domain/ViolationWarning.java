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
 * 学生违规警告表 这是分管领导发给学生的
 * </p>
 *
 * @author team01
 * @since 2024-07-11 09:44:01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("violation_warning")
@ApiModel(value = "ViolationWarning对象", description = "学生违规警告表 这是分管领导发给学生的")
public class ViolationWarning implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表student 谁违规了")
    @TableField("student_id")
    private Long studentId;

    @ApiModelProperty("被抓的时间")
    @TableField("violation_time")
    private LocalDateTime violationTime;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("违规的程度 0记过 1严重警告 2警告")
    @TableField("degree")
    private Integer degree;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
