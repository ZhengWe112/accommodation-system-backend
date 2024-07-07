package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 宿舍管理员表
 * </p>
 *
 * @author team01
 * @since 2024-07-07 06:15:11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dormitory_administrator")
@ApiModel(value = "DormitoryAdministrator对象", description = "宿舍管理员表")
public class DormitoryAdministrator implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表building 这个宿管管理哪栋")
    @TableField("building_id")
    private Long buildingId;

    @ApiModelProperty("外键 关联到表role 这个宿管的角色 默认都是3 一般不会改变")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("全名")
    @TableField("fullname")
    private String fullname;

    @ApiModelProperty("工号 用于登录 固定为13位 虽然也是以id结尾 但它不是外键")
    @TableField("job_id")
    private String jobId;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
