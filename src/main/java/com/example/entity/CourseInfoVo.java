package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course_info_vo
 */
@TableName(value ="course_info_vo")
@Data
public class CourseInfoVo implements Serializable {
    /**
     * 课程号
     */
    @TableField(value = "course_id")
    private String courseId;

    /**
     * 课程名
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 学院名
     */
    @TableField(value = "college_name")
    private String collegeName;

    /**
     * 学时
     */
    @TableField(value = "credit_hours")
    private Byte creditHours;

    /**
     * 学分
     */
    @TableField(value = "credit")
    private Double credit;

    /**
     * 主分类
     */
    @TableField(value = "course_type")
    private String courseType;

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
        CourseInfoVo other = (CourseInfoVo) that;
        return (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getCollegeName() == null ? other.getCollegeName() == null : this.getCollegeName().equals(other.getCollegeName()))
            && (this.getCreditHours() == null ? other.getCreditHours() == null : this.getCreditHours().equals(other.getCreditHours()))
            && (this.getCredit() == null ? other.getCredit() == null : this.getCredit().equals(other.getCredit()))
            && (this.getCourseType() == null ? other.getCourseType() == null : this.getCourseType().equals(other.getCourseType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getCollegeName() == null) ? 0 : getCollegeName().hashCode());
        result = prime * result + ((getCreditHours() == null) ? 0 : getCreditHours().hashCode());
        result = prime * result + ((getCredit() == null) ? 0 : getCredit().hashCode());
        result = prime * result + ((getCourseType() == null) ? 0 : getCourseType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseId=").append(courseId);
        sb.append(", courseName=").append(courseName);
        sb.append(", collegeName=").append(collegeName);
        sb.append(", creditHours=").append(creditHours);
        sb.append(", credit=").append(credit);
        sb.append(", courseType=").append(courseType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}