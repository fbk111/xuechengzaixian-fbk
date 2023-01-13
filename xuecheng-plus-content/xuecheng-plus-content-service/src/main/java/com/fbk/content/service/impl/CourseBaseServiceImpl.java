package com.fbk.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbk.base.model.PageParams;
import com.fbk.base.model.PageResult;
import com.fbk.content.mapper.CourseBaseMapper;
import com.fbk.content.model.dto.QueryCourseParamsDto;
import com.fbk.content.model.po.CourseBase;
import com.fbk.content.service.CourseBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * <p>
 * 课程基本信息 服务实现类
 * </p>
 *
 * @author fbk
 */
@Slf4j
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements CourseBaseService {

    @Autowired

    private CourseBaseMapper courseBaseMapper;

    /**
     *
     * @param pageParams 分页参数:提供当前的默认的page页数和page一页显示的个数
     * @param courseParamsDto 查询条件
     * @return
     */
    @Override
    public PageResult<CourseBase> list(PageParams pageParams, QueryCourseParamsDto courseParamsDto) {
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(courseParamsDto.getCourseName()),CourseBase::getName,courseParamsDto.getCourseName());
        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,courseParamsDto.getAuditStatus());
        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getPublishStatus()),CourseBase::getAuditStatus,courseParamsDto.getPublishStatus());
        //创建page需要no和size
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        //selectPage需要new Page和queryWrapper
        Page<CourseBase> pageResult=courseBaseMapper.selectPage(page,queryWrapper);
        //获取到查询的参数
        List<CourseBase> records = pageResult.getRecords();
        //获取到整个数目
        long total = pageResult.getTotal();
        return new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());
    }
}
