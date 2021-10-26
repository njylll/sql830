package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.CourseInfo;
import com.example.service.CourseInfoService;
import com.example.dao.CourseInfoMapper;
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
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo>
    implements CourseInfoService
{

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    /**
     *添加
     * */
    public void add(CourseInfo courseinfo)
    {
        courseinfo.setUuid(uuid.getRandomIdByUUID());
        courseInfoMapper.insert(courseinfo);
    }


    public void update(CourseInfo courseInfo) {
        courseInfoMapper.updateById(courseInfo);
    }


    public List<CourseInfo> listAll()
    {
        return courseInfoMapper.listAll();
    }

    public CourseInfo queryByCourseId(String id)
    {
        return courseInfoMapper.selectOne(new QueryWrapper<CourseInfo>().eq("course_id",id));
    }

    @Override
    public List<String> searchAllCourseName() {
        return courseInfoMapper.findAllCourseName();
    }

    public List<String> listAllCourseId()
    {
        return courseInfoMapper.listAllId();
    }
}




