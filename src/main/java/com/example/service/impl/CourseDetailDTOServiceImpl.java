package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CourseDetailMapper;
import com.example.dao.CourseTimeMapper;
import com.example.dto.CourseDetailDTO;
import com.example.entity.CourseDetail;
import com.example.entity.CourseTime;
import com.example.service.CourseDetailDTOService;
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




}




