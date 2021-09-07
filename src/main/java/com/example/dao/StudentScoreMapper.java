package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CourseInfo;
import com.example.entity.StudentScore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.example.dao.StudentScore
 */
@Mapper
public interface StudentScoreMapper extends BaseMapper<StudentScore> {
    List<StudentScore> listAll();
}




