package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName student_course_vo
 */
@TableName(value ="student_course_vo")
@Data
public class StudentCourseVo implements Serializable {
    /**
     * 
     */
    @TableField(value = "student_id")
    private String studentId;

    /**
     * 
     */
    @TableField(value = "course_name")
    private String courseName;

    @TableField(value = "teacher_name")
    private String teacherName;

    @TableField(value = "teaching_location")
    private String teachingLocation;

    /**
     * 
     */
    @TableField(value = "day_time")
    private String dayTime;

    /**
     * 
     */
    @TableField(value = "section_start")
    private String sectionStart;

    /**
     * 
     */
    @TableField(value = "section_end")
    private String sectionEnd;

    /**
     * 
     */
    @TableField(value = "start_week")
    private Byte startWeek;

    /**
     * 
     */
    @TableField(value = "end_week")
    private Byte endWeek;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StudentCourseVo other = (StudentCourseVo) that;
        return (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getDayTime() == null ? other.getDayTime() == null : this.getDayTime().equals(other.getDayTime()))
            && (this.getSectionStart() == null ? other.getSectionStart() == null : this.getSectionStart().equals(other.getSectionStart()))
            && (this.getSectionEnd() == null ? other.getSectionEnd() == null : this.getSectionEnd().equals(other.getSectionEnd()))
            && (this.getStartWeek() == null ? other.getStartWeek() == null : this.getStartWeek().equals(other.getStartWeek()))
            && (this.getEndWeek() == null ? other.getEndWeek() == null : this.getEndWeek().equals(other.getEndWeek()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getDayTime() == null) ? 0 : getDayTime().hashCode());
        result = prime * result + ((getSectionStart() == null) ? 0 : getSectionStart().hashCode());
        result = prime * result + ((getSectionEnd() == null) ? 0 : getSectionEnd().hashCode());
        result = prime * result + ((getStartWeek() == null) ? 0 : getStartWeek().hashCode());
        result = prime * result + ((getEndWeek() == null) ? 0 : getEndWeek().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", studentId=").append(studentId);
        sb.append(", courseName=").append(courseName);
        sb.append(", dayTime=").append(dayTime);
        sb.append(", sectionStart=").append(sectionStart);
        sb.append(", sectionEnd=").append(sectionEnd);
        sb.append(", startWeek=").append(startWeek);
        sb.append(", endWeek=").append(endWeek);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}