package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CourseVoMapper;
import com.example.entity.CourseInfo;
import com.example.entity.CourseVo;
import com.example.service.CourseInfoService;
import com.example.dao.CourseInfoMapper;
import com.example.service.CourseVoService;
import com.example.util.UUIDGenerator;
import com.example.util.uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class CourseVoServiceImpl extends ServiceImpl<CourseVoMapper, CourseVo>
        implements CourseVoService
{

    @Autowired
    private CourseVoMapper courseVoMapper;

    /**
     *添加
     * */


    public List<CourseVo> listAll()
    {
        return courseVoMapper.listAll();
    }

    public CourseVo queryByCourseId(String id)
    {
        return courseVoMapper.selectOne(new QueryWrapper<CourseVo>().eq("course_id",id));
    }

    @Override
    public List<String> searchAllCourseId() {
        return courseVoMapper.findAllCourseId();
    }


    public List<String> searchAllCourseName()
    {

    }
}






