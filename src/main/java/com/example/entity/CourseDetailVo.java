package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName course_detail_vo
 */
@TableName(value ="course_detail_vo")
@Data
public class CourseDetailVo implements Serializable {
    /**
     * 课程号
     */
    @TableField(value = "course_id")
    private String courseId;

    /**
     * 
     */
    @TableField(value = "course_detail_id")
    private String courseDetailId;

    /**
     * 开始学年
     */
    @TableField(value = "start_school_year")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startSchoolYear;

    /**
     * 结束学年
     */
    @TableField(value = "end_school_year")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endSchoolYear;

    /**
     * 开设学期
     */
    @TableField(value = "start_term")
    private Byte startTerm;

    /**
     * 课程状态
     */
    @TableField(value = "course_condition")
    private String courseCondition;

    /**
     * 教师名
     */
    @TableField(value = "teacher_name")
    private String teacherName;

    /**
     * 授课地点
     */
    @TableField(value = "teaching_location")
    private String teachingLocation;

    /**
     * 课程名
     */
    @TableField(value = "course_name")
    private String courseName;

    @TableField(value = "course_type")
    private String courseType;
    @TableField(value = "credit_hours")
    private Integer creditHours;
    @TableField(value = "credit")
    private Float credit;


    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDetailVo that = (CourseDetailVo) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(courseDetailId, that.courseDetailId) && Objects.equals(startSchoolYear, that.startSchoolYear) && Objects.equals(endSchoolYear, that.endSchoolYear) && Objects.equals(startTerm, that.startTerm) && Objects.equals(courseCondition, that.courseCondition) && Objects.equals(teacherName, that.teacherName) && Objects.equals(teachingLocation, that.teachingLocation) && Objects.equals(courseName, that.courseName) && Objects.equals(courseType, that.courseType) && Objects.equals(creditHours, that.creditHours) && Objects.equals(credit, that.credit);
    }

    @Override
    public String toString() {
        return "CourseDetailVo{" +
                "courseId='" + courseId + '\'' +
                ", courseDetailId='" + courseDetailId + '\'' +
                ", startSchoolYear=" + startSchoolYear +
                ", endSchoolYear=" + endSchoolYear +
                ", startTerm=" + startTerm +
                ", courseCondition='" + courseCondition + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teachingLocation='" + teachingLocation + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", creditHours=" + creditHours +
                ", credit=" + credit +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseDetailId, startSchoolYear, endSchoolYear, startTerm, courseCondition, teacherName, teachingLocation, courseName, courseType, creditHours, credit);
    }

}