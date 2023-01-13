package com.fbk.content.api;

import com.fbk.base.model.PageParams;
import com.fbk.base.model.PageResult;
import com.fbk.content.model.dto.QueryCourseParamsDto;
import com.fbk.content.model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 房博坤
 * @Date 2023/1/13 14:52
 * @Version 1.0.1
 */
@Api()
@RestController
public class CourseBaseInfoController {

    @ApiOperation("课程查询接口")
    @PostMapping("course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto courseParamsDto){

        return null;
    }
}
