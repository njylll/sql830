package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 课程具体日期
 * @TableName course_time
 */
@TableName(value ="course_time")
@Data
public class CourseTime implements Serializable {
    @TableField(value = "course_time_id")
    private String courseTimeId;

    @Override
    public String toString() {
        return "CourseTime{" +
                "courseTimeId='" + courseTimeId + '\'' +
                ", courseDetailId='" + courseDetailId + '\'' +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", dayTime='" + dayTime + '\'' +
                ", sectionStart='" + sectionStart + '\'' +
                ", sectionEnd='" + sectionEnd + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseTime that = (CourseTime) o;
        return Objects.equals(getCourseTimeId(), that.getCourseTimeId()) && Objects.equals(getCourseDetailId(), that.getCourseDetailId()) && Objects.equals(getStartWeek(), that.getStartWeek()) && Objects.equals(getEndWeek(), that.getEndWeek()) && Objects.equals(getDayTime(), that.getDayTime()) && Objects.equals(getSectionStart(), that.getSectionStart()) && Objects.equals(getSectionEnd(), that.getSectionEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseTimeId(), getCourseDetailId(), getStartWeek(), getEndWeek(), getDayTime(), getSectionStart(), getSectionEnd());
    }

    /**
     * 课程详情号
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

    @TableField(value = "section_end")
    private String sectionEnd;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}