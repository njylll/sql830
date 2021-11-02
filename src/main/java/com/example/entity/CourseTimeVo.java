package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName course_time_vo
 */
@TableName(value ="course_time_vo")
@Data
public class CourseTimeVo implements Serializable {
    /**
     * 课程名
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 
     */
    @TableField(value = "course_detail_id")
    private String courseDetailId;

    /**
     * 开始周
     */
    @TableField(value = "start_week")
    private Byte startWeek;

    /**
     * 结束周
     */
    @TableField(value = "end_week")
    private Byte endWeek;

    /**
     * 一星期内的日次，如1,3,4
     */
    @TableField(value = "day_time")
    private String dayTime;

    /**
     * 节次，每天用;分割，如1-2,6-7;3-4
     */
    @TableField(value = "section_start")
    private String sectionStart;

    /**
     * 
     */
    @TableField(value = "section_end")
    private String sectionEnd;

    @TableField(value = "course_time_id")
    private String courseTimeId;

    @Override
    public String toString() {
        return "CourseTimeVo{" +
                "courseName='" + courseName + '\'' +
                ", courseDetailId='" + courseDetailId + '\'' +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", dayTime='" + dayTime + '\'' +
                ", sectionStart='" + sectionStart + '\'' +
                ", sectionEnd='" + sectionEnd + '\'' +
                ", courseTimeId='" + courseTimeId + '\'' +
                ", startSchoolYear=" + startSchoolYear +
                ", endSchoolYear=" + endSchoolYear +
                ", startTerm=" + startTerm +
                ", courseCondition='" + courseCondition + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseTimeVo that = (CourseTimeVo) o;
        return Objects.equals(getCourseName(), that.getCourseName()) && Objects.equals(getCourseDetailId(), that.getCourseDetailId()) && Objects.equals(getStartWeek(), that.getStartWeek()) && Objects.equals(getEndWeek(), that.getEndWeek()) && Objects.equals(getDayTime(), that.getDayTime()) && Objects.equals(getSectionStart(), that.getSectionStart()) && Objects.equals(getSectionEnd(), that.getSectionEnd()) && Objects.equals(getCourseTimeId(), that.getCourseTimeId()) && Objects.equals(getStartSchoolYear(), that.getStartSchoolYear()) && Objects.equals(getEndSchoolYear(), that.getEndSchoolYear()) && Objects.equals(getStartTerm(), that.getStartTerm()) && Objects.equals(getCourseCondition(), that.getCourseCondition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseName(), getCourseDetailId(), getStartWeek(), getEndWeek(), getDayTime(), getSectionStart(), getSectionEnd(), getCourseTimeId(), getStartSchoolYear(), getEndSchoolYear(), getStartTerm(), getCourseCondition());
    }

    /**
     * 开始学年
     */
    @TableField(value = "start_school_year")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startSchoolYear;

    /**
     * 结束学年
     */
    @TableField(value = "end_school_year")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endSchoolYear;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}