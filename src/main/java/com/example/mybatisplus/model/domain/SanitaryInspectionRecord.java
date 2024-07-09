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
 * 卫生检查明细表
 * </p>
 *
 * @author team01
 * @since 2024-07-08 07:22:02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sanitary_inspection_record")
@ApiModel(value = "SanitaryInspectionRecord对象", description = "卫生检查明细表")
public class SanitaryInspectionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表room 这条明细属于哪个寝室")
    @TableField("room_id")
    private Long roomId;

    @ApiModelProperty("外键 关联到表sanitary_inspection 这条明细属于哪次卫生检查")
    @TableField("sanitary_inspection_id")
    private Long sanitaryInspectionId;

    @ApiModelProperty("项 如床铺 桌面")
    @TableField("item")
    private String item;

    @ApiModelProperty("具体项的得分，由item、score、以及寝室评分字典应该可以算出总分")
    @TableField("score")
    private Float score;

    @ApiModelProperty("对该项的描述 比如哪里不合格导致扣的分")
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
