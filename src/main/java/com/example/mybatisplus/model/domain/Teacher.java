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
 *
 * </p>
 *
 * @author team01
 * @since 2024-07-08 03:15:07
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

    @ApiModelProperty("外键 关联到表role 教师的角色 默认都是5 一般不会改变")
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
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
