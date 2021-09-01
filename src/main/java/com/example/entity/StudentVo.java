package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName student_vo
 */
@TableName(value ="student_vo")
@Data
public class StudentVo implements Serializable {
    /**
     * 学号
     */
    @TableField(value = "student_id")
    private String studentId;

    /**
     * 姓名
     */
    @TableField(value = "student_name")
    private String studentName;

    /**
     * 性别,0男,1女
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 出生日期
     */
    @TableField(value = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 班号
     */
    @TableField(value = "class_id")
    private String classId;

    /**
     * 学院名
     */
    @TableField(value = "college_name")
    private String collegeName;

    /**
     * 专业名称
     */
    @TableField(value = "major_name")
    private String majorName;

    /**
     * 入学日期
     */
    @TableField(value = "admission_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissionDate;

    /**
     * 学制，如4年
     */
    @TableField(value = "schooling_period")
    private Byte schoolingPeriod;

    /**
     * 学生状态，如在校、退学
     */
    @TableField(value = "student_status")
    private String studentStatus;

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
        StudentVo other = (StudentVo) that;
        return (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getStudentName() == null ? other.getStudentName() == null : this.getStudentName().equals(other.getStudentName()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getCollegeName() == null ? other.getCollegeName() == null : this.getCollegeName().equals(other.getCollegeName()))
            && (this.getMajorName() == null ? other.getMajorName() == null : this.getMajorName().equals(other.getMajorName()))
            && (this.getAdmissionDate() == null ? other.getAdmissionDate() == null : this.getAdmissionDate().equals(other.getAdmissionDate()))
            && (this.getSchoolingPeriod() == null ? other.getSchoolingPeriod() == null : this.getSchoolingPeriod().equals(other.getSchoolingPeriod()))
            && (this.getStudentStatus() == null ? other.getStudentStatus() == null : this.getStudentStatus().equals(other.getStudentStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getCollegeName() == null) ? 0 : getCollegeName().hashCode());
        result = prime * result + ((getMajorName() == null) ? 0 : getMajorName().hashCode());
        result = prime * result + ((getAdmissionDate() == null) ? 0 : getAdmissionDate().hashCode());
        result = prime * result + ((getSchoolingPeriod() == null) ? 0 : getSchoolingPeriod().hashCode());
        result = prime * result + ((getStudentStatus() == null) ? 0 : getStudentStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", studentId=").append(studentId);
        sb.append(", studentName=").append(studentName);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", classId=").append(classId);
        sb.append(", collegeName=").append(collegeName);
        sb.append(", majorName=").append(majorName);
        sb.append(", admissionDate=").append(admissionDate);
        sb.append(", schoolingPeriod=").append(schoolingPeriod);
        sb.append(", studentStatus=").append(studentStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}