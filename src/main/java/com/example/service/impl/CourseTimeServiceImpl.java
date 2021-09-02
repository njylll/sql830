package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CourseTimeMapper;
import com.example.entity.CourseTime;
import com.example.service.CourseTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CourseTimeServiceImpl extends ServiceImpl<CourseTimeMapper, CourseTime>
    implements CourseTimeService{

    @Autowired
    private CourseTimeMapper courseTimeMapper;
    public CourseTime queryByCourseDetailId(String courseDetailId)
    {
        return courseTimeMapper.selectOne(new QueryWrapper<CourseTime>().eq("course_detail_id",courseDetailId));
    }

}




