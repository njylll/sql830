package com.example.service;

import com.example.dto.CourseDTO;

import java.util.List;

public interface CourseDTOService {

    public CourseDTO queryByCourseID(String courseId);

    public List<CourseDTO> listAll();
}
