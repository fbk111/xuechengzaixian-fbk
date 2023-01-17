package com.fbk.content.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fbk.content.model.dto.CourseCategoryTreeDto
import com.fbk.content.model.po.CourseCategory
import java.io.Serializable

/**
 * @Author 房博坤
 * @Date 2023/1/17 20:35
 *  @Version 1.0.1
 */
interface CourseCategoryMapper:BaseMapper<CourseCategory>{
   fun selectTreeNodes(id:String):List<CourseCategoryTreeDto>
}