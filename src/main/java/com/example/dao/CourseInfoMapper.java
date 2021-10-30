package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.dao.CourseInfo
 */
@Mapper
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {
    List<CourseInfo> listAll();
    List<String> findAllCourseName();
    void updateCourseIdByCourseId(@Param("courseId") String courseId,@Param("newCourseId") String newCourseId);
    void updateCourseNameByCourseId(@Param("courseId") String courseId,@Param("courseName") String courseName);
    void updateCollegeIdByCourseId(@Param("courseId") String courseId,@Param("collegeId") String collegeId);
    void updateCreditHoursByCourseId(@Param("courseId") String courseId,@Param("creditHour") String creditHour);
    void updateCreditByCourseId(@Param("courseId") String courseId,@Param("credit") String credit);
    void updateCourseTypeByCourseId(@Param("courseId") String courseId,@Param("courseType") String courseType);
}




