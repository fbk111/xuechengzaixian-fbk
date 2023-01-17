package com.fbk.content.api;

import com.fbk.content.model.dto.CourseCategoryTreeDto;
import com.fbk.content.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 房博坤
 * @Date 2023/1/14 22:59
 * @Version 1.0.1
 */
@Api(value = "课程分类",tags = "课程分类接口")
@RestController
public class CourseCategoryController {
@Autowired
private CourseCategoryService courseCategoryService;
@ApiOperation("查询所有分类")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes(){
       return courseCategoryService.queryTreeNodes("1");
    }
}
