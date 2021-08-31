package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.StudentInfo;
import com.example.service.StudentInfoService;
import com.example.dao.StudentInfoMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo>
    implements StudentInfoService{

}




