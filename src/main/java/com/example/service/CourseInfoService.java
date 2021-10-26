package com.example.service;

import com.example.entity.CourseInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface CourseInfoService extends IService<CourseInfo> {
    public void add(CourseInfo courseinfo);

    public void update(CourseInfo courseInfo);

    public List<CourseInfo> listAll();

    public CourseInfo queryByCourseId(String id);

    List<String> searchAllCourseName();

}
