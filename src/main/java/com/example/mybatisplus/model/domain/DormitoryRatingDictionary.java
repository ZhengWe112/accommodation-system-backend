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
 * 寝室评分字典 怎么去计算寝室的得分
 * </p>
 *
 * @author team01
 * @since 2024-07-10 01:53:12
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dormitory_rating_dictionary")
@ApiModel(value = "DormitoryRatingDictionary对象", description = "寝室评分字典 怎么去计算寝室的得分")
public class DormitoryRatingDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("项 与评分相关的项 如成绩 卫生")
    @TableField("item")
    private String item;

    @ApiModelProperty("该项的占比")
    @TableField("proportion")
    private Float proportion;

    @ApiModelProperty("描述 比如怎么做可以得多少分")
    @TableField("description")
    private String description;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
