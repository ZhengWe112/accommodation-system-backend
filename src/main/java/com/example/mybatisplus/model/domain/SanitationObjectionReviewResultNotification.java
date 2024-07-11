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
 * 卫生异议审核结果通知表 这是分管领导发给宿管的通知 表示这次异议给不给过 若过了宿管要修改相应的得分
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:38:35
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sanitation_objection_review_result_notification")
@ApiModel(value = "SanitationObjectionReviewResultNotification对象", description = "卫生异议审核结果通知表 这是分管领导发给宿管的通知 表示这次异议给不给过 若过了宿管要修改相应的得分")
public class SanitationObjectionReviewResultNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表dormitory_administrator 发给哪个宿管的")
    @TableField("dormitory_administrator_id")
    private Long dormitoryAdministratorId;

    @ApiModelProperty("描述 比如把xxx寝室的xxx项改为xxx分")
    @TableField("description")
    private String description;

    @ApiModelProperty("0表示这次异议被通过了 1表示这次异议不予通过 2表示通过的异议已经被执行")
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
