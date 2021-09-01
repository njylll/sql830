package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 可开设的课程
 * @TableName course_info
 */
@TableName(value ="course_info")
@Data
public class CourseInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "UUID")
    private String uuid;

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
     * 开设此门课的学院
     */
    @TableField(value = "responsible_college_id")
    private String responsibleCollegeId;

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

    /**
     * 备用分类
     */
    @TableField(value = "course_type_2")
    private String courseType2;

    /**
     * 教学途径
     */
    @TableField(value = "teaching_way")
    private String teachingWay;

    /**
     * 考核方式
     */
    @TableField(value = "assessment_method")
    private String assessmentMethod;

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
        CourseInfo other = (CourseInfo) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getResponsibleCollegeId() == null ? other.getResponsibleCollegeId() == null : this.getResponsibleCollegeId().equals(other.getResponsibleCollegeId()))
            && (this.getCreditHours() == null ? other.getCreditHours() == null : this.getCreditHours().equals(other.getCreditHours()))
            && (this.getCredit() == null ? other.getCredit() == null : this.getCredit().equals(other.getCredit()))
            && (this.getCourseType() == null ? other.getCourseType() == null : this.getCourseType().equals(other.getCourseType()))
            && (this.getCourseType2() == null ? other.getCourseType2() == null : this.getCourseType2().equals(other.getCourseType2()))
            && (this.getTeachingWay() == null ? other.getTeachingWay() == null : this.getTeachingWay().equals(other.getTeachingWay()))
            && (this.getAssessmentMethod() == null ? other.getAssessmentMethod() == null : this.getAssessmentMethod().equals(other.getAssessmentMethod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getResponsibleCollegeId() == null) ? 0 : getResponsibleCollegeId().hashCode());
        result = prime * result + ((getCreditHours() == null) ? 0 : getCreditHours().hashCode());
        result = prime * result + ((getCredit() == null) ? 0 : getCredit().hashCode());
        result = prime * result + ((getCourseType() == null) ? 0 : getCourseType().hashCode());
        result = prime * result + ((getCourseType2() == null) ? 0 : getCourseType2().hashCode());
        result = prime * result + ((getTeachingWay() == null) ? 0 : getTeachingWay().hashCode());
        result = prime * result + ((getAssessmentMethod() == null) ? 0 : getAssessmentMethod().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", courseId=").append(courseId);
        sb.append(", courseName=").append(courseName);
        sb.append(", responsibleCollegeId=").append(responsibleCollegeId);
        sb.append(", creditHours=").append(creditHours);
        sb.append(", credit=").append(credit);
        sb.append(", courseType=").append(courseType);
        sb.append(", courseType2=").append(courseType2);
        sb.append(", teachingWay=").append(teachingWay);
        sb.append(", assessmentMethod=").append(assessmentMethod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}