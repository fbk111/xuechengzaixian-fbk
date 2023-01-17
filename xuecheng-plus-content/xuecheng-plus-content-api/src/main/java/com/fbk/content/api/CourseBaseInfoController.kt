package com.fbk.content.api

import com.fbk.base.model.PageParams
import com.fbk.base.model.PageResult
import com.fbk.content.model.dto.AddCourseDto
import com.fbk.content.model.dto.CourseBaseInfoDto
import com.fbk.content.model.dto.QueryCourseParamsDto
import com.fbk.content.model.po.CourseBase
import com.fbk.content.service.CourseBaseService
import com.fbk.content.service.CourseCategoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @Author 房博坤
 * @Date 2023/1/17 21:44
 *  @Version 1.0.1
 */
@Api("课程基本信息")
@RestController
class CourseBaseInfoController {

    @Autowired
    lateinit  var courseBaseService:CourseBaseService

    fun list(pageParams: PageParams,@RequestBody courseParamsDto: QueryCourseParamsDto):PageResult<CourseBase>{
       return  courseBaseService.list(pageParams,courseParamsDto)
    }

    @ApiOperation("新增课程")
    @PostMapping("/course")
    fun createCourseBase(@RequestBody addCourseDto: AddCourseDto):CourseBaseInfoDto{
        val companyId=22L
        var createCourseBase = courseBaseService.createCourseBase(companyId, addCourseDto)
        return createCourseBase
    }
}