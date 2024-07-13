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
 * 学生住退宿申请表
 * </p>
 *
 * @author team01
 * @since 2024-07-13 01:31:07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("accommodation_application")
@ApiModel(value = "AccommodationApplication对象", description = "学生住退宿申请表")
public class AccommodationApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表student 这条记录是谁申请的")
    @TableField("student_id")
    private Long studentId;

    @TableField(exist = false)
    private String studentName;

    @ApiModelProperty("申请时间")
    @TableField("request_time")
    private LocalDateTime requestTime;

    @ApiModelProperty("申请类型 0表示普通入住申请 1表示普通调宿申请 2表示互换申请 3表示个人退宿申请 4表示校外住宿申请")
    @TableField("request_type")
    private Integer requestType;

    @ApiModelProperty("申请理由")
    @TableField("request_reason")
    private String requestReason;

    @ApiModelProperty("申请状态 0表示等待审核 1表示审核通过 2表示被驳回")
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
