package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "course_vo")
public class CourseVo implements Serializable {

  private String courseId;
  private String courseName;
  private String teacherName;
  private String teachingLocation;
  private String assessmentMethod;
  private String courseType;
  private String courseDetailId;
  private String studentId;
}
