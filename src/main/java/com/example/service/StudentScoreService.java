package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.CourseInfo;
import com.example.entity.StudentScore;

import java.util.List;

/**
 *
 */
public interface StudentScoreService extends IService<StudentScore> {
    public void add(StudentScore studentScore);

    public void update(StudentScore StudentScore);

    public List<StudentScore> listAll();

}
