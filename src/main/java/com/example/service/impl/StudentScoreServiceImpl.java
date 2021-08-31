package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.StudentScore;
import com.example.service.StudentScoreService;
import com.example.dao.StudentScoreMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StudentScoreServiceImpl extends ServiceImpl<StudentScoreMapper, StudentScore>
    implements StudentScoreService{

}




