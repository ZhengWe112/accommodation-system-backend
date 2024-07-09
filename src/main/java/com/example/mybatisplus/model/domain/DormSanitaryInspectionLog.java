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
 * 宿舍卫生检查日志表
 * </p>
 *
 * @author team01
 * @since 2024-07-09 09:20:04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dorm_sanitary_inspection_log")
@ApiModel(value = "DormSanitaryInspectionLog对象", description = "宿舍卫生检查日志表")
public class DormSanitaryInspectionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表room 这条日志是哪个寝室的")
    @TableField("room_id")
    private Long roomId;

    @ApiModelProperty("外键 关联到表inspection_id 哪次卫生检查")
    @TableField("sanitary_inspection_id")
    private Long sanitaryInspectionId;

    @ApiModelProperty("成绩")
    @TableField("score")
    private Float score;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
