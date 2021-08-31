package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

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
    private String UUID;

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
     * 辅导员
     */
    @TableField(value = "counselor_name")
    private String counselor_name;

    /**
     * 班主任
     */
    @TableField(value = "class_teacher_name")
    private String class_teacher_name;

    /**
     * 班长
     */
    @TableField(value = "monitor_student_id")
    private String monitor_student_id;

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
        return (this.getUUID() == null ? other.getUUID() == null : this.getUUID().equals(other.getUUID()))
            && (this.getClass_id() == null ? other.getClass_id() == null : this.getClass_id().equals(other.getClass_id()))
            && (this.getCollege_id() == null ? other.getCollege_id() == null : this.getCollege_id().equals(other.getCollege_id()))
            && (this.getMajor_id() == null ? other.getMajor_id() == null : this.getMajor_id().equals(other.getMajor_id()))
            && (this.getCounselor_name() == null ? other.getCounselor_name() == null : this.getCounselor_name().equals(other.getCounselor_name()))
            && (this.getClass_teacher_name() == null ? other.getClass_teacher_name() == null : this.getClass_teacher_name().equals(other.getClass_teacher_name()))
            && (this.getMonitor_student_id() == null ? other.getMonitor_student_id() == null : this.getMonitor_student_id().equals(other.getMonitor_student_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUUID() == null) ? 0 : getUUID().hashCode());
        result = prime * result + ((getClass_id() == null) ? 0 : getClass_id().hashCode());
        result = prime * result + ((getCollege_id() == null) ? 0 : getCollege_id().hashCode());
        result = prime * result + ((getMajor_id() == null) ? 0 : getMajor_id().hashCode());
        result = prime * result + ((getCounselor_name() == null) ? 0 : getCounselor_name().hashCode());
        result = prime * result + ((getClass_teacher_name() == null) ? 0 : getClass_teacher_name().hashCode());
        result = prime * result + ((getMonitor_student_id() == null) ? 0 : getMonitor_student_id().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", UUID=").append(UUID);
        sb.append(", class_id=").append(class_id);
        sb.append(", college_id=").append(college_id);
        sb.append(", major_id=").append(major_id);
        sb.append(", counselor_name=").append(counselor_name);
        sb.append(", class_teacher_name=").append(class_teacher_name);
        sb.append(", monitor_student_id=").append(monitor_student_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}