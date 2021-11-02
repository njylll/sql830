package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CourseInfo;
import com.example.entity.CourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.dao.CollegeInfo
 */
@Mapper
public interface CourseVoMapper extends BaseMapper<CourseVo> {
    List<CourseVo> listAll();

    List<String> findAllCourseId();
}




