package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

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
    private String UUID;

    /**
     * 课程号
     */
    @TableField(value = "course_id")
    private String course_id;

    /**
     * 课程名
     */
    @TableField(value = "course_name")
    private String course_name;

    /**
     * 开设此门课的学院
     */
    @TableField(value = "responsible_college_id")
    private String responsible_college_id;

    /**
     * 学时
     */
    @TableField(value = "credit_hours")
    private Byte credit_hours;

    /**
     * 学分
     */
    @TableField(value = "credit")
    private Double credit;

    /**
     * 主分类
     */
    @TableField(value = "course_type")
    private String course_type;

    /**
     * 备用分类
     */
    @TableField(value = "course_type_2")
    private String course_type_2;

    /**
     * 教学途径
     */
    @TableField(value = "teaching_way")
    private String teaching_way;

    /**
     * 考核方式
     */
    @TableField(value = "assessment_method")
    private String assessment_method;

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
        return (this.getUUID() == null ? other.getUUID() == null : this.getUUID().equals(other.getUUID()))
            && (this.getCourse_id() == null ? other.getCourse_id() == null : this.getCourse_id().equals(other.getCourse_id()))
            && (this.getCourse_name() == null ? other.getCourse_name() == null : this.getCourse_name().equals(other.getCourse_name()))
            && (this.getResponsible_college_id() == null ? other.getResponsible_college_id() == null : this.getResponsible_college_id().equals(other.getResponsible_college_id()))
            && (this.getCredit_hours() == null ? other.getCredit_hours() == null : this.getCredit_hours().equals(other.getCredit_hours()))
            && (this.getCredit() == null ? other.getCredit() == null : this.getCredit().equals(other.getCredit()))
            && (this.getCourse_type() == null ? other.getCourse_type() == null : this.getCourse_type().equals(other.getCourse_type()))
            && (this.getCourse_type_2() == null ? other.getCourse_type_2() == null : this.getCourse_type_2().equals(other.getCourse_type_2()))
            && (this.getTeaching_way() == null ? other.getTeaching_way() == null : this.getTeaching_way().equals(other.getTeaching_way()))
            && (this.getAssessment_method() == null ? other.getAssessment_method() == null : this.getAssessment_method().equals(other.getAssessment_method()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUUID() == null) ? 0 : getUUID().hashCode());
        result = prime * result + ((getCourse_id() == null) ? 0 : getCourse_id().hashCode());
        result = prime * result + ((getCourse_name() == null) ? 0 : getCourse_name().hashCode());
        result = prime * result + ((getResponsible_college_id() == null) ? 0 : getResponsible_college_id().hashCode());
        result = prime * result + ((getCredit_hours() == null) ? 0 : getCredit_hours().hashCode());
        result = prime * result + ((getCredit() == null) ? 0 : getCredit().hashCode());
        result = prime * result + ((getCourse_type() == null) ? 0 : getCourse_type().hashCode());
        result = prime * result + ((getCourse_type_2() == null) ? 0 : getCourse_type_2().hashCode());
        result = prime * result + ((getTeaching_way() == null) ? 0 : getTeaching_way().hashCode());
        result = prime * result + ((getAssessment_method() == null) ? 0 : getAssessment_method().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", UUID=").append(UUID);
        sb.append(", course_id=").append(course_id);
        sb.append(", course_name=").append(course_name);
        sb.append(", responsible_college_id=").append(responsible_college_id);
        sb.append(", credit_hours=").append(credit_hours);
        sb.append(", credit=").append(credit);
        sb.append(", course_type=").append(course_type);
        sb.append(", course_type_2=").append(course_type_2);
        sb.append(", teaching_way=").append(teaching_way);
        sb.append(", assessment_method=").append(assessment_method);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}