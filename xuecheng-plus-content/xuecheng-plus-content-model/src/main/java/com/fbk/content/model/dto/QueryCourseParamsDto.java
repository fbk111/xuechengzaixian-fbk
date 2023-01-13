package com.fbk.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Author 房博坤
 * @Date 2023/1/13 14:43
 * @Version 1.0.1
 */
@Data
@ToString
public class QueryCourseParamsDto {

    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;
}
