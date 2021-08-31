package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程具体日期
 * @TableName course_time
 */
@TableName(value ="course_time")
@Data
public class CourseTime implements Serializable {
    /**
     * 
     */
    @TableField(value = "course_id")
    private String course_id;

    /**
     * 开始周
     */
    @TableField(value = "start_week")
    private Byte start_week;

    /**
     * 结束周
     */
    @TableField(value = "end_week")
    private Byte end_week;

    /**
     * 一星期内的日次，如1,3,4
     */
    @TableField(value = "day_time")
    private String day_time;

    /**
     * 节次，每天用;分割，如1-2,6-7;3-4
     */
    @TableField(value = "section")
    private String section;

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
        CourseTime other = (CourseTime) that;
        return (this.getCourse_id() == null ? other.getCourse_id() == null : this.getCourse_id().equals(other.getCourse_id()))
            && (this.getStart_week() == null ? other.getStart_week() == null : this.getStart_week().equals(other.getStart_week()))
            && (this.getEnd_week() == null ? other.getEnd_week() == null : this.getEnd_week().equals(other.getEnd_week()))
            && (this.getDay_time() == null ? other.getDay_time() == null : this.getDay_time().equals(other.getDay_time()))
            && (this.getSection() == null ? other.getSection() == null : this.getSection().equals(other.getSection()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourse_id() == null) ? 0 : getCourse_id().hashCode());
        result = prime * result + ((getStart_week() == null) ? 0 : getStart_week().hashCode());
        result = prime * result + ((getEnd_week() == null) ? 0 : getEnd_week().hashCode());
        result = prime * result + ((getDay_time() == null) ? 0 : getDay_time().hashCode());
        result = prime * result + ((getSection() == null) ? 0 : getSection().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", course_id=").append(course_id);
        sb.append(", start_week=").append(start_week);
        sb.append(", end_week=").append(end_week);
        sb.append(", day_time=").append(day_time);
        sb.append(", section=").append(section);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}