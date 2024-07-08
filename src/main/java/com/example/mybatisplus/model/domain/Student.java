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
 * @since 2024-07-08 03:17:30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("student")
@ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表bed 这个学生的床位")
    @TableField("bed_id")
    private Long bedId;

    @ApiModelProperty("外键 关联到表role 学生的角色 默认都是6 一般不会改变")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("全名")
    @TableField("fullname")
    private String fullname;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("学号 固定为13为 虽然以id结尾 但它不是外键")
    @TableField("student_id")
    private String studentId;

    @ApiModelProperty("所在学院 为了简化 我们没有学院表 学院被认为是系统外的东西")
    @TableField("collage_name")
    private String collageName;

    @ApiModelProperty("专业 为了简化 我们没有专业表 专业被认为是系统外的东西")
    @TableField("major")
    private String major;

    @ApiModelProperty("班级 为了简化 我们没有班级表 班级被认为是系统外的东西")
    @TableField("classname")
    private String classname;

    @ApiModelProperty("年级")
    @TableField("grade")
    private Integer grade;

    @ApiModelProperty("是否住校 0表示住校 1表示不住校")
    @TableField("is_dormitory_resident")
    private Boolean isDormitoryResident;

    @ApiModelProperty("是否在校 0表示在校 1表示不在校。即使是一个住校生 也未必在校")
    @TableField("is_present_on_campus")
    private Boolean isPresentOnCampus;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
