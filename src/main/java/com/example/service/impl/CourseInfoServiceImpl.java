package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.CourseInfo;
import com.example.service.CourseInfoService;
import com.example.dao.CourseInfoMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo>
    implements CourseInfoService{

}



