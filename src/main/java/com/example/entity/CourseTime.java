package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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
    private String courseId;

    /**
     * 开始周
     */
    @TableField(value = "start_week")
    private Byte startWeek;

    /**
     * 结束周
     */
    @TableField(value = "end_week")
    private Byte endWeek;

    /**
     * 一星期内的日次，如1,3,4
     */
    @TableField(value = "day_time")
    private String dayTime;

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
        return (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getStartWeek() == null ? other.getStartWeek() == null : this.getStartWeek().equals(other.getStartWeek()))
            && (this.getEndWeek() == null ? other.getEndWeek() == null : this.getEndWeek().equals(other.getEndWeek()))
            && (this.getDayTime() == null ? other.getDayTime() == null : this.getDayTime().equals(other.getDayTime()))
            && (this.getSection() == null ? other.getSection() == null : this.getSection().equals(other.getSection()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getStartWeek() == null) ? 0 : getStartWeek().hashCode());
        result = prime * result + ((getEndWeek() == null) ? 0 : getEndWeek().hashCode());
        result = prime * result + ((getDayTime() == null) ? 0 : getDayTime().hashCode());
        result = prime * result + ((getSection() == null) ? 0 : getSection().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseId=").append(courseId);
        sb.append(", startWeek=").append(startWeek);
        sb.append(", endWeek=").append(endWeek);
        sb.append(", dayTime=").append(dayTime);
        sb.append(", section=").append(section);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}