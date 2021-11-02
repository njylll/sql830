package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.CollegeInfo;
import com.example.entity.CourseInfo;
import com.example.entity.CourseVo;

import java.util.List;

/**
 *
 */
public interface CourseVoService extends IService<CourseVo> {
    public void add(CourseVo courseVo);

    public void update(CourseVo courseVo);

    public List<CourseVo> listAll();

    public CourseVo queryByCourseId(String id);

    List<String> searchAllCourseName();
}
