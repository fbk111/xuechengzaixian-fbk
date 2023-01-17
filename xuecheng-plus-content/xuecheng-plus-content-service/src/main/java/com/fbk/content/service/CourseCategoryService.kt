package com.fbk.content.service

import com.fbk.content.model.dto.CourseCategoryTreeDto

/**
 * @Author 房博坤
 * @Date 2023/1/17 20:45
 *  @Version 1.0.1
 */
interface CourseCategoryService {

    /**
     * 课程分类查询
     */
    fun queryTreeNodes(id:String):List<CourseCategoryTreeDto>

}