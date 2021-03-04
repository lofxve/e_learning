package com.wzd.eduservice.entity.capter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName VideoVo
 * @Author lofxve
 * @Date 2021/2/25 12:22
 * @Version 1.0
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String videoSourceId;
    private Boolean isFree;
}
