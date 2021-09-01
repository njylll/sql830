package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 班级信息
 * @TableName class_info
 */
@TableName(value ="class_info")
@Data
public class ClassInfo implements Serializable {
    /**
     * 唯一标识符

     */
    @TableId(value = "UUID")
    private String uuid;

    /**
     * 班号
     */
    @TableField(value = "class_id")
    private String classId;

    /**
     * 学院号
     */
    @TableField(value = "college_id")
    private String collegeId;

    /**
     * 专业号
     */
    @TableField(value = "major_id")
    private String majorId;

    /**
     * 辅导员
     */
    @TableField(value = "counselor_name")
    private String counselorName;

    /**
     * 班主任
     */
    @TableField(value = "class_teacher_name")
    private String classTeacherName;

    /**
     * 班长
     */
    @TableField(value = "monitor_student_id")
    private String monitorStudentId;

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
        ClassInfo other = (ClassInfo) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getCollegeId() == null ? other.getCollegeId() == null : this.getCollegeId().equals(other.getCollegeId()))
            && (this.getMajorId() == null ? other.getMajorId() == null : this.getMajorId().equals(other.getMajorId()))
            && (this.getCounselorName() == null ? other.getCounselorName() == null : this.getCounselorName().equals(other.getCounselorName()))
            && (this.getClassTeacherName() == null ? other.getClassTeacherName() == null : this.getClassTeacherName().equals(other.getClassTeacherName()))
            && (this.getMonitorStudentId() == null ? other.getMonitorStudentId() == null : this.getMonitorStudentId().equals(other.getMonitorStudentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getCollegeId() == null) ? 0 : getCollegeId().hashCode());
        result = prime * result + ((getMajorId() == null) ? 0 : getMajorId().hashCode());
        result = prime * result + ((getCounselorName() == null) ? 0 : getCounselorName().hashCode());
        result = prime * result + ((getClassTeacherName() == null) ? 0 : getClassTeacherName().hashCode());
        result = prime * result + ((getMonitorStudentId() == null) ? 0 : getMonitorStudentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", classId=").append(classId);
        sb.append(", collegeId=").append(collegeId);
        sb.append(", majorId=").append(majorId);
        sb.append(", counselorName=").append(counselorName);
        sb.append(", classTeacherName=").append(classTeacherName);
        sb.append(", monitorStudentId=").append(monitorStudentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}