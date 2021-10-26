package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.example.dao.CourseInfo
 */
@Mapper
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {
    List<CourseInfo> listAll();
    List<String> findAllCourseName();
    List<String> listAllId();
}




