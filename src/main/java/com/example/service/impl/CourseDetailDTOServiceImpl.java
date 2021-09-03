package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CourseDetailMapper;
import com.example.dao.CourseTimeMapper;
import com.example.dto.CourseDetailDTO;
import com.example.entity.CourseDetail;
import com.example.entity.CourseTime;
import com.example.service.CourseDetailDTOService;
import com.example.service.CourseDetailService;
import com.example.service.CourseInfoService;
import com.example.service.CourseTimeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
@Service
public class CourseDetailDTOServiceImpl implements CourseDetailDTOService
{
    @Autowired
    private CourseDetailService courseDetailService;
    @Autowired
    private CourseDetailMapper courseDetailMapper;

    @Autowired
    private CourseTimeService courseTimeService;

    public List<CourseDetailDTO> queryByCourseId(String id)
    {
        List courseDetailDTOList = new ArrayList<CourseDetailDTO>();
        List<CourseDetail> courseDetailList = courseDetailMapper.selectList(new QueryWrapper<CourseDetail>().eq("course_id",id));
        CourseDetailDTO courseDetailDTO = new CourseDetailDTO();
        Iterator<CourseDetail> it = courseDetailList.iterator();
        while (it.hasNext()) {
            BeanUtils.copyProperties(it,courseDetailDTO);
            CourseTime courseTime = courseTimeService.queryByCourseDetailId(it.next().getCourseDetailId());
            courseDetailDTO.setDayTime(courseTime.getDayTime());
            courseDetailDTO.setStartWeek(courseTime.getStartWeek());
            courseDetailDTO.setEndWeek(courseTime.getEndWeek());
            courseDetailDTO.setSection(courseTime.getSection());
            courseDetailDTOList.add(courseDetailDTO);
        }

        return courseDetailDTOList;
    }

    public void add(CourseDetailDTO courseDetailDTO)
    {
        CourseDetail courseDetail = new CourseDetail();
        CourseTime courseTime = new CourseTime();
        BeanUtils.copyProperties(courseDetailDTO,courseDetail);
        courseTime.setCourseDetailId(courseDetailDTO.getCourseDetailId());
        courseTime.setDayTime(courseDetailDTO.getDayTime());
        courseTime.setSection(courseDetailDTO.getSection());
        courseTime.setEndWeek(courseDetailDTO.getEndWeek());
        courseTime.setStartWeek(courseDetailDTO.getStartWeek());
        courseTimeService.save(courseTime);
        courseDetailService.save(courseDetail);
    }

    public CourseDetailDTO queryByCourseDetailId(String detailId)
    {
        CourseTime courseTime = courseTimeService.getOne(new QueryWrapper<CourseTime>().eq("course_detail_id", detailId));
        CourseDetail courseDetail = courseDetailService.getOne(new QueryWrapper<CourseDetail>().eq("course_detail_id", detailId));
        CourseDetailDTO courseDetailDTO = new CourseDetailDTO();
        BeanUtils.copyProperties(courseTime,courseDetailDTO);
        BeanUtils.copyProperties(courseDetail,courseDetailDTO);
        return courseDetailDTO;
    }

    public void update(CourseDetailDTO courseDetailDTO)
    {
        CourseDetail courseDetail = new CourseDetail();
        CourseTime courseTime = new CourseTime();
        BeanUtils.copyProperties(courseDetailDTO,courseDetail);
        courseTime.setCourseDetailId(courseDetailDTO.getCourseDetailId());
        courseTime.setDayTime(courseDetailDTO.getDayTime());
        courseTime.setSection(courseDetailDTO.getSection());
        courseTime.setEndWeek(courseDetailDTO.getEndWeek());
        courseTime.setStartWeek(courseDetailDTO.getStartWeek());
        courseDetailService.update(courseDetail,new QueryWrapper<CourseDetail>().eq("course_detail_id",courseDetailDTO.getCourseDetailId()));
        courseTimeService.update(courseTime,new QueryWrapper<CourseTime>().eq("course_detail_id",courseDetailDTO.getCourseDetailId()));

    }

    public void remove(String courseDetailId)
    {
        courseDetailService.remove(new QueryWrapper<CourseDetail>().eq("course_detail_id",courseDetailId));
        courseTimeService.remove(new QueryWrapper<CourseTime>().eq("course_detail_id",courseDetailId));
    }
}




