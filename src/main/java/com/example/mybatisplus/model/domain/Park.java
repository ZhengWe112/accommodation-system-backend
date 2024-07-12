package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 园区表
 * </p>
 *
 * @author team01
 * @since 2024-07-12 05:37:56
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("park")
@ApiModel(value = "Park对象", description = "园区表")
public class Park implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("园区名")
    @TableField("park_name")
    private String parkName;

    @ApiModelProperty("地址")
    @TableField("park_address")
    private String parkAddress;

    @ApiModelProperty("类型 0表示教师园区 1表示男生 2表示女生")
    @TableField("park_type")
    private Integer parkType;

    @ApiModelProperty("竣工时间")
    @TableField("complicated_time")
    private LocalDate complicatedTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;


}
