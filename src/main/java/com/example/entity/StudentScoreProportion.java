package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 课程的成绩比例分配
 * @TableName student_score_proportion
 */
@TableName(value ="student_score_proportion")
@Data
public class StudentScoreProportion implements Serializable {
    /**
     * 课程号
     */
    @TableField(value = "course_id")
    private String courseId;

    /**
     * 平时比例
     */
    @TableField(value = "usual_proportion")
    private Byte usualProportion;

    /**
     * 期中比例
     */
    @TableField(value = "mid_term_proportion")
    private Byte midTermProportion;

    /**
     * 期末比例
     */
    @TableField(value = "final_term_proportion")
    private Byte finalTermProportion;

    /**
     * 备用比例
     */
    @TableField(value = "extra_proportion")
    private Byte extraProportion;

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
        StudentScoreProportion other = (StudentScoreProportion) that;
        return (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getUsualProportion() == null ? other.getUsualProportion() == null : this.getUsualProportion().equals(other.getUsualProportion()))
            && (this.getMidTermProportion() == null ? other.getMidTermProportion() == null : this.getMidTermProportion().equals(other.getMidTermProportion()))
            && (this.getFinalTermProportion() == null ? other.getFinalTermProportion() == null : this.getFinalTermProportion().equals(other.getFinalTermProportion()))
            && (this.getExtraProportion() == null ? other.getExtraProportion() == null : this.getExtraProportion().equals(other.getExtraProportion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getUsualProportion() == null) ? 0 : getUsualProportion().hashCode());
        result = prime * result + ((getMidTermProportion() == null) ? 0 : getMidTermProportion().hashCode());
        result = prime * result + ((getFinalTermProportion() == null) ? 0 : getFinalTermProportion().hashCode());
        result = prime * result + ((getExtraProportion() == null) ? 0 : getExtraProportion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseId=").append(courseId);
        sb.append(", usualProportion=").append(usualProportion);
        sb.append(", midTermProportion=").append(midTermProportion);
        sb.append(", finalTermProportion=").append(finalTermProportion);
        sb.append(", extraProportion=").append(extraProportion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}