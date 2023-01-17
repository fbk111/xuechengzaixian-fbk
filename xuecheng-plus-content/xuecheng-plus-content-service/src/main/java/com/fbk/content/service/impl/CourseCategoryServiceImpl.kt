package com.fbk.content.service.impl

import com.fbk.content.mapper.CourseCategoryMapper
import com.fbk.content.model.dto.CourseCategoryTreeDto
import com.fbk.content.model.po.CourseCategory
import com.fbk.content.service.CourseCategoryService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList
import java.util.HashMap

/**
 * @Author 房博坤
 * @Date 2023/1/17 20:47
 *  @Version 1.0.1
 */
@Service
@Slf4j
class CourseCategoryServiceImpl:CourseCategoryService {

    @Autowired
    lateinit var courseCategoryMapper:CourseCategoryMapper
    override fun queryTreeNodes(id: String): List<CourseCategoryTreeDto> {
        var categoryTreeDtos = courseCategoryMapper.selectTreeNodes(id)
        var courseCategoryTreeDto=ArrayList<CourseCategoryTreeDto>()
        var nodeMap=HashMap<String,CourseCategoryTreeDto>()
        categoryTreeDtos.stream().forEach{
            if (it.parentid.equals(id)) courseCategoryTreeDto.add(it)
            else{
                var parentid = it.parentid
                var parentNode = nodeMap[parentid]
                if(parentNode!=null){
                    var childrenTreeNodes = parentNode.childrenTreeNodes
                    if(childrenTreeNodes!=null) parentNode.childrenTreeNodes= ArrayList()
                    parentNode.childrenTreeNodes.add(it)
                }
            }
        }
        return courseCategoryTreeDto
    }
}