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
 * 住退宿日志 记录了所有学生的所有历史申请
 * </p>
 *
 * @author team01
 * @since 2024-07-13 01:31:36
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("accommodation_log")
@ApiModel(value = "AccommodationLog对象", description = "住退宿日志 记录了所有学生的所有历史申请")
public class AccommodationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表responsible_leader 这条记录是谁审核的")
    @TableField("responsible_leader_id")
    private Long responsibleLeaderId;

    @TableField(exist = false)
    private String responsibleLeaderName;

    @ApiModelProperty("外键 关联到表student 这条记录是谁申请的")
    @TableField("student_id")
    private Long studentId;

    @TableField(exist = false)
    private String studentName;

    @ApiModelProperty("此次申请的时间")
    @TableField("request_time")
    private LocalDateTime requestTime;

    @ApiModelProperty("此次申请的类型 0表示普通入住申请 1表示普通调宿申请 2表示互换申请 3表示个人退宿申请 4表示校外住宿申请")
    @TableField("request_type")
    private Integer requestType;

    @ApiModelProperty("此次申请的理由")
    @TableField("request_reason")
    private String requestReason;

    @ApiModelProperty("审核通过或者驳回的时间")
    @TableField("review_time")
    private LocalDateTime reviewTime;

    @ApiModelProperty("此次申请是通过还是驳回 0通过 1驳回")
    @TableField("review_state")
    private Boolean reviewState;

    @ApiModelProperty("当review_state==1时才有意义 表示驳回的原因")
    @TableField("review_reason")
    private String reviewReason;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
