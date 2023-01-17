package com.fbk.content.model.dto;

import com.fbk.content.model.po.CourseCategory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 房博坤
 * @Date 2023/1/14 22:51
 * @Version 1.0.1
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory {

    List<CourseCategoryTreeDto> childrenTreeNodes;
}
