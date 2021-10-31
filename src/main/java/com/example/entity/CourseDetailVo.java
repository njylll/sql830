package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
        CourseDetailVo other = (CourseDetailVo) that;
        return (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCourseDetailId() == null ? other.getCourseDetailId() == null : this.getCourseDetailId().equals(other.getCourseDetailId()))
            && (this.getStartSchoolYear() == null ? other.getStartSchoolYear() == null : this.getStartSchoolYear().equals(other.getStartSchoolYear()))
            && (this.getEndSchoolYear() == null ? other.getEndSchoolYear() == null : this.getEndSchoolYear().equals(other.getEndSchoolYear()))
            && (this.getStartTerm() == null ? other.getStartTerm() == null : this.getStartTerm().equals(other.getStartTerm()))
            && (this.getCourseCondition() == null ? other.getCourseCondition() == null : this.getCourseCondition().equals(other.getCourseCondition()))
            && (this.getTeacherName() == null ? other.getTeacherName() == null : this.getTeacherName().equals(other.getTeacherName()))
            && (this.getTeachingLocation() == null ? other.getTeachingLocation() == null : this.getTeachingLocation().equals(other.getTeachingLocation()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCourseDetailId() == null) ? 0 : getCourseDetailId().hashCode());
        result = prime * result + ((getStartSchoolYear() == null) ? 0 : getStartSchoolYear().hashCode());
        result = prime * result + ((getEndSchoolYear() == null) ? 0 : getEndSchoolYear().hashCode());
        result = prime * result + ((getStartTerm() == null) ? 0 : getStartTerm().hashCode());
        result = prime * result + ((getCourseCondition() == null) ? 0 : getCourseCondition().hashCode());
        result = prime * result + ((getTeacherName() == null) ? 0 : getTeacherName().hashCode());
        result = prime * result + ((getTeachingLocation() == null) ? 0 : getTeachingLocation().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseId=").append(courseId);
        sb.append(", courseDetailId=").append(courseDetailId);
        sb.append(", startSchoolYear=").append(startSchoolYear);
        sb.append(", endSchoolYear=").append(endSchoolYear);
        sb.append(", startTerm=").append(startTerm);
        sb.append(", courseCondition=").append(courseCondition);
        sb.append(", teacherName=").append(teacherName);
        sb.append(", teachingLocation=").append(teachingLocation);
        sb.append(", courseName=").append(courseName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}