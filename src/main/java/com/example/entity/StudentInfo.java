package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学生信息
 * @TableName student_info
 */
@TableName(value ="student_info")
@Data
public class StudentInfo implements Serializable {
    /**
     * 唯一标识符
     */
    @TableId(value = "UUID")
    private String UUID;

    /**
     * 学号
     */
    @TableField(value = "student_id")
    private String student_id;

    /**
     * 姓名
     */
    @TableField(value = "student_name")
    private String student_name;

    /**
     * 性别,0男,1女
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 出生日期
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 班号
     */
    @TableField(value = "class_id")
    private String class_id;

    /**
     * 学院号
     */
    @TableField(value = "college_id")
    private String college_id;

    /**
     * 专业号
     */
    @TableField(value = "major_id")
    private String major_id;

    /**
     * 入学日期
     */
    @TableField(value = "admission_date")
    private Date admission_date;

    /**
     * 学制，如4年
     */
    @TableField(value = "schooling_period")
    private Byte schooling_period;

    /**
     * 学生状态，如在校、退学
     */
    @TableField(value = "student_status")
    private String student_status;

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
        StudentInfo other = (StudentInfo) that;
        return (this.getUUID() == null ? other.getUUID() == null : this.getUUID().equals(other.getUUID()))
            && (this.getStudent_id() == null ? other.getStudent_id() == null : this.getStudent_id().equals(other.getStudent_id()))
            && (this.getStudent_name() == null ? other.getStudent_name() == null : this.getStudent_name().equals(other.getStudent_name()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getClass_id() == null ? other.getClass_id() == null : this.getClass_id().equals(other.getClass_id()))
            && (this.getCollege_id() == null ? other.getCollege_id() == null : this.getCollege_id().equals(other.getCollege_id()))
            && (this.getMajor_id() == null ? other.getMajor_id() == null : this.getMajor_id().equals(other.getMajor_id()))
            && (this.getAdmission_date() == null ? other.getAdmission_date() == null : this.getAdmission_date().equals(other.getAdmission_date()))
            && (this.getSchooling_period() == null ? other.getSchooling_period() == null : this.getSchooling_period().equals(other.getSchooling_period()))
            && (this.getStudent_status() == null ? other.getStudent_status() == null : this.getStudent_status().equals(other.getStudent_status()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUUID() == null) ? 0 : getUUID().hashCode());
        result = prime * result + ((getStudent_id() == null) ? 0 : getStudent_id().hashCode());
        result = prime * result + ((getStudent_name() == null) ? 0 : getStudent_name().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getClass_id() == null) ? 0 : getClass_id().hashCode());
        result = prime * result + ((getCollege_id() == null) ? 0 : getCollege_id().hashCode());
        result = prime * result + ((getMajor_id() == null) ? 0 : getMajor_id().hashCode());
        result = prime * result + ((getAdmission_date() == null) ? 0 : getAdmission_date().hashCode());
        result = prime * result + ((getSchooling_period() == null) ? 0 : getSchooling_period().hashCode());
        result = prime * result + ((getStudent_status() == null) ? 0 : getStudent_status().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", UUID=").append(UUID);
        sb.append(", student_id=").append(student_id);
        sb.append(", student_name=").append(student_name);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", class_id=").append(class_id);
        sb.append(", college_id=").append(college_id);
        sb.append(", major_id=").append(major_id);
        sb.append(", admission_date=").append(admission_date);
        sb.append(", schooling_period=").append(schooling_period);
        sb.append(", student_status=").append(student_status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}