package com.IT.service.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/1/13 13:02
 */
@Data
public class TeacherQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;
//
    @ApiModelProperty(value = "讲师姓名")
    private String name;
    @ApiModelProperty(value = "讲师级别")
    private Integer level;
    @ApiModelProperty(value = "开始时间")
    private String joinDateBegin;
    @ApiModelProperty(value = ("结束时间"))
    private String joinDateEnd;
}
