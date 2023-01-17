package com.fbk.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fbk.base.model.PageParams;
import com.fbk.base.model.PageResult;
import com.fbk.content.model.dto.AddCourseDto;
import com.fbk.content.model.dto.CourseBaseInfoDto;
import com.fbk.content.model.dto.QueryCourseParamsDto;
import com.fbk.content.model.po.CourseBase;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 课程基本信息 服务类
 * </p>
 *
 * @author fbk
 * @since 2023-01-13
 */
public interface CourseBaseService extends IService<CourseBase> {
    /**
     * @deprecated 课程查询
     * @param pageParams 分页参数
     * @param courseParamsDto 查询条件
     * @return
     */
    PageResult<CourseBase> list(PageParams pageParams, QueryCourseParamsDto courseParamsDto);

    /**
     *
     * @param companyId 培训结构id
     * @param addCourseDto 添加课程信息
     * @return
     */
    CourseBaseInfoDto createCourseBase(Long companyId,AddCourseDto addCourseDto);

}
