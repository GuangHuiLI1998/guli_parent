package com.IT.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.IT.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程简介
 * </p>
 *
 * @author 小李
 * @since 2023-01-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("edu_course_description")
@ApiModel(value="CourseDescription对象", description="课程简介")
public class CourseDescription extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程简介")
    private String description;


}
