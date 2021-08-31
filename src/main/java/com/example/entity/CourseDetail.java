package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

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
    private String course_id;

    /**
     * 开始学年
     */
    @TableField(value = "start_school_year")
    private Object start_school_year;

    /**
     * 结束学年
     */
    @TableField(value = "end_school_year")
    private Object end_school_year;

    /**
     * 开设学期
     */
    @TableField(value = "start_term")
    private Byte start_term;

    /**
     * 课程状态
     */
    @TableField(value = "course_condition")
    private String course_condition;

    /**
     * 教师名
     */
    @TableField(value = "teacher_name")
    private String teacher_name;

    /**
     * 授课地点
     */
    @TableField(value = "teaching_location")
    private String teaching_location;

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
        return (this.getCourse_id() == null ? other.getCourse_id() == null : this.getCourse_id().equals(other.getCourse_id()))
            && (this.getStart_school_year() == null ? other.getStart_school_year() == null : this.getStart_school_year().equals(other.getStart_school_year()))
            && (this.getEnd_school_year() == null ? other.getEnd_school_year() == null : this.getEnd_school_year().equals(other.getEnd_school_year()))
            && (this.getStart_term() == null ? other.getStart_term() == null : this.getStart_term().equals(other.getStart_term()))
            && (this.getCourse_condition() == null ? other.getCourse_condition() == null : this.getCourse_condition().equals(other.getCourse_condition()))
            && (this.getTeacher_name() == null ? other.getTeacher_name() == null : this.getTeacher_name().equals(other.getTeacher_name()))
            && (this.getTeaching_location() == null ? other.getTeaching_location() == null : this.getTeaching_location().equals(other.getTeaching_location()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourse_id() == null) ? 0 : getCourse_id().hashCode());
        result = prime * result + ((getStart_school_year() == null) ? 0 : getStart_school_year().hashCode());
        result = prime * result + ((getEnd_school_year() == null) ? 0 : getEnd_school_year().hashCode());
        result = prime * result + ((getStart_term() == null) ? 0 : getStart_term().hashCode());
        result = prime * result + ((getCourse_condition() == null) ? 0 : getCourse_condition().hashCode());
        result = prime * result + ((getTeacher_name() == null) ? 0 : getTeacher_name().hashCode());
        result = prime * result + ((getTeaching_location() == null) ? 0 : getTeaching_location().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", course_id=").append(course_id);
        sb.append(", start_school_year=").append(start_school_year);
        sb.append(", end_school_year=").append(end_school_year);
        sb.append(", start_term=").append(start_term);
        sb.append(", course_condition=").append(course_condition);
        sb.append(", teacher_name=").append(teacher_name);
        sb.append(", teaching_location=").append(teaching_location);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}