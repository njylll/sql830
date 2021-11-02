package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CourseVoMapper;
import com.example.entity.CourseVo;
import com.example.service.CourseVoService;
import org.springframework.stereotype.Service;

@Service
public class CourseVoServiceImpl extends ServiceImpl<CourseVoMapper, CourseVo>  implements CourseVoService
{
}
