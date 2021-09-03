package com.example.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.entity.CourseDetail;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Component
public class CourseDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String courseId;
    private String courseName;
    private String responsibleCollegeId;
    private Byte creditHours;
    private Double credit;
    private String courseType;
    private String courseType2;
    private String teachingWay;
    private String assessmentMethod;

    /**
     * 课程详情列表
     */
    List<CourseDetailDTO> courseDetailList;

}