package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 已经开始（或结束）的课程
 * @TableName course_detail
 */
@TableName(value ="course_detail")
@Data
public class CourseDetail implements Serializable {
    /**
     * 课程号
     */
    @TableField(value = "course_id")
    private String courseId;

    /**
     * 课程详情号
     */
    @TableField(value = "course_detail_id")
    private String courseDetailId;

    /**
     * 开始学年
     */
    @TableField(value = "start_school_year")
    @DateTimeFormat(pattern = "yyyy")
    private LocalDate startSchoolYear;

    /**
     * 结束学年
     */
    @TableField(value = "end_school_year")
    @DateTimeFormat(pattern = "yyyy")
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
    @TableField(value = "teacher_id")
    private String teacherId;

    /**
     * 授课地点
     */
    @TableField(value = "teaching_location")
    private String teachingLocation;

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
        CourseDetail other = (CourseDetail) that;
        return (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getStartSchoolYear() == null ? other.getStartSchoolYear() == null : this.getStartSchoolYear().equals(other.getStartSchoolYear()))
            && (this.getEndSchoolYear() == null ? other.getEndSchoolYear() == null : this.getEndSchoolYear().equals(other.getEndSchoolYear()))
            && (this.getStartTerm() == null ? other.getStartTerm() == null : this.getStartTerm().equals(other.getStartTerm()))
            && (this.getCourseCondition() == null ? other.getCourseCondition() == null : this.getCourseCondition().equals(other.getCourseCondition()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getTeachingLocation() == null ? other.getTeachingLocation() == null : this.getTeachingLocation().equals(other.getTeachingLocation()))
                &&(this.getCourseDetailId() == null ? other.getCourseDetailId() == null : this.getCourseDetailId().equals(other.getCourseDetailId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getStartSchoolYear() == null) ? 0 : getStartSchoolYear().hashCode());
        result = prime * result + ((getEndSchoolYear() == null) ? 0 : getEndSchoolYear().hashCode());
        result = prime * result + ((getStartTerm() == null) ? 0 : getStartTerm().hashCode());
        result = prime * result + ((getCourseCondition() == null) ? 0 : getCourseCondition().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getTeachingLocation() == null) ? 0 : getTeachingLocation().hashCode());
        result = prime * result + ((getCourseDetailId() == null) ? 0 : getCourseDetailId().hashCode());
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
        sb.append(", teacherId=").append(teacherId);
        sb.append(", teachingLocation=").append(teachingLocation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}