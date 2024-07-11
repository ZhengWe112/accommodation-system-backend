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
 * 房间表
 * </p>
 *
 * @author team01
 * @since 2024-07-11 03:26:17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("room")
@ApiModel(value = "Room对象", description = "房间表")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("外键 关联到表building 房间属于哪栋楼")
    @TableField("building_id")
    private Long buildingId;

    @ApiModelProperty("房间号固定为4为数字 为2位楼栋编号+2位数字")
    @TableField("room_number")
    private String roomNumber;

    @ApiModelProperty("房间类型 0寝室 1辅导员室 2门卫室 3办公室 4储藏室 5洗手间 6洗室 7服务台 8服务间")
    @TableField("room_type")
    private Integer roomType;

    @ApiModelProperty("房间所在的楼层 在几楼")
    @TableField("room_floor")
    private Integer roomFloor;

    @ApiModelProperty("房间在左手边还是右手边")
    @TableField("room_location")
    private Boolean roomLocation;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
