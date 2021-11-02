package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CourseVo;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Mapper
public interface CourseVoMapper extends BaseMapper<CourseVo> {


   List<String> searchAllCourseName();
   List<String> listAll();
}

