package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.CourseDTO;
import com.example.entity.CourseDetail;

import java.util.List;

public interface CourseDTOService {

    public CourseDTO queryByCourseID(String courseId);

    public List<CourseDTO> listAll();
}
