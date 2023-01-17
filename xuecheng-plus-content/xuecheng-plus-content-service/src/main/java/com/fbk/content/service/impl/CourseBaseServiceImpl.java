package com.fbk.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fbk.base.model.PageParams;
import com.fbk.base.model.PageResult;
import com.fbk.content.mapper.CourseBaseMapper;
import com.fbk.content.mapper.CourseCategoryMapper;
import com.fbk.content.mapper.CourseMarketMapper;
import com.fbk.content.model.dto.AddCourseDto;
import com.fbk.content.model.dto.CourseBaseInfoDto;
import com.fbk.content.model.dto.QueryCourseParamsDto;
import com.fbk.content.model.po.CourseBase;
import com.fbk.content.model.po.CourseCategory;
import com.fbk.content.model.po.CourseMarket;
import com.fbk.content.service.CourseBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    private CourseMarketMapper courseMarketMapper;

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

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

    @Override
    @Transactional
    public CourseBaseInfoDto createCourseBase(Long companyId,AddCourseDto dto) {
        //对参数合法性校验
        if (StringUtils.isBlank(dto.getName())) {
            throw new RuntimeException("课程名称为空");
        }

        if (StringUtils.isBlank(dto.getMt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isBlank(dto.getSt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isBlank(dto.getGrade())) {
            throw new RuntimeException("课程等级为空");
        }

        if (StringUtils.isBlank(dto.getTeachmode())) {
            throw new RuntimeException("教育模式为空");
        }

        if (StringUtils.isBlank(dto.getUsers())) {
            throw new RuntimeException("适应人群为空");
        }

        if (StringUtils.isBlank(dto.getCharge())) {
            throw new RuntimeException("收费规则为空");
        }
        CourseBase courseBase = new CourseBase();
//        直接使用映射
//        courseBase.setName(dto.getName());
//        courseBase.setMt(dto.getMt());
        BeanUtils.copyProperties(dto,courseBase);
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setAuditStatus("202002");//默认设置问题叫
        courseBase.setStatus("203001");

        int insert = courseBaseMapper.insert(courseBase);
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto,courseMarket);
        courseMarket.setId(courseBase.getId());
        //校验课程选择收费，价格必须填写
        String charge = dto.getCharge();
        if(charge.equals("201001")){//收费
            if(dto.getPrice()==null){
                throw new RuntimeException("价格没有");
            }
        }
        int insert1 = courseMarketMapper.insert(courseMarket);
        if(insert<=0||insert1<=0){
            throw new RuntimeException("添加课程失败");
        }
        return getCourseBaseInfo(companyId);
    }

    private CourseBaseInfoDto getCourseBaseInfo(Long courseId){
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        CourseBaseInfoDto courseBaseInfoDto=new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
        String mt = courseBase.getMt();
        String st = courseBase.getSt();
        CourseCategory mtCategory = courseCategoryMapper.selectById(mt);
        CourseCategory stCategory = courseCategoryMapper.selectById(st);
        if(mtCategory!=null){
            String mtName = mtCategory.getName();
            courseBaseInfoDto.setMtName(mtName);
        }
        if(stCategory!=null){
            String stName = stCategory.getName();
            courseBaseInfoDto.setStName(stName);
        }
        return courseBaseInfoDto;
    }
}
