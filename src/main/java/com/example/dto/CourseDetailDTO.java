package com.example.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseDetailDTO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String courseId;
    private String courseDetailId;
    private Object startSchoolYear;
    private Object endSchoolYear;
    private Byte startTerm;
    private String courseCondition;
    private String teacherName;
    private String teachingLocation;

    /**
     * 课程时间
     */
    private Byte startWeek;
    private Byte endWeek;
    private String dayTime;
    private String section;
}
