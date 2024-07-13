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
 * 住退宿通知表 这是分管领导发给宿舍管理员的通知
 * </p>
 *
 * @author team01
 * @since 2024-07-13 03:16:46
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("accommodation_notification")
@ApiModel(value = "AccommodationNotification对象", description = "住退宿通知表 这是分管领导发给宿舍管理员的通知")
public class AccommodationNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表dormitory_administrator 表示这条通知是发给哪个宿管")
    @TableField("dormitory_administrator_id")
    private Long dormitoryAdministratorId;

    @ApiModelProperty("通知类型 0批量排宿通知 1批量排宿通知 2普通退宿通知 3批量退宿通知 4同楼栋换宿通知")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("通知附加信息（如果没有就为空）")
    @TableField("message")
    private String message;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
