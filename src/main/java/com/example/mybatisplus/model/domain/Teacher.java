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
 *
 * </p>
 *
 * @author team01
 * @since 2024-07-07 06:15:11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("teacher")
@ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表bed 这个教师的床位")
    @TableField("bed_id")
    private Long bedId;

    @ApiModelProperty("外键 关联到表role 维修管理员的角色 默认都是5 一般不会改变")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("全名")
    @TableField("fullname")
    private String fullname;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("教师号 固定为13为 虽然以id结尾 但它不是外键")
    @TableField("teacher_id")
    private String teacherId;

    @ApiModelProperty("职称")
    @TableField("professional_title")
    private Integer professionalTitle;

    @ApiModelProperty("学院")
    @TableField("collage_name")
    private String collageName;

    @ApiModelProperty("专业")
    @TableField("major")
    private String major;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
