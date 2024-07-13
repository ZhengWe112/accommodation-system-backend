package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Teacher对象", description="")
public class Teacher extends Model<Teacher>{
    private static final long serialVersionUID = 1L;
    // fullname professional_title teacher_id collage_name major
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("fullname")
    private String fullname;

    @TableField("professional_title")
    private String professionalTitle;

    @TableField("teacher_id")
    private String teacherId;

    @TableField("collage_name")
    private String collageName;

    @TableField("major")
    private String major;

}
