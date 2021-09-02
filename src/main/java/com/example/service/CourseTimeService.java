package com.example.service;

import com.example.entity.CourseTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface CourseTimeService extends IService<CourseTime> {
    public CourseTime queryByCourseDetailId(String courseDetailId);
}
