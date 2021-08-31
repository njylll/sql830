package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

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
    private String course_id;

    /**
     * 平时比例
     */
    @TableField(value = "usual_proportion")
    private Byte usual_proportion;

    /**
     * 期中比例
     */
    @TableField(value = "mid_term_proportion")
    private Byte mid_term_proportion;

    /**
     * 期末比例
     */
    @TableField(value = "final_term_proportion")
    private Byte final_term_proportion;

    /**
     * 备用比例
     */
    @TableField(value = "extra_proportion")
    private Byte extra_proportion;

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
        return (this.getCourse_id() == null ? other.getCourse_id() == null : this.getCourse_id().equals(other.getCourse_id()))
            && (this.getUsual_proportion() == null ? other.getUsual_proportion() == null : this.getUsual_proportion().equals(other.getUsual_proportion()))
            && (this.getMid_term_proportion() == null ? other.getMid_term_proportion() == null : this.getMid_term_proportion().equals(other.getMid_term_proportion()))
            && (this.getFinal_term_proportion() == null ? other.getFinal_term_proportion() == null : this.getFinal_term_proportion().equals(other.getFinal_term_proportion()))
            && (this.getExtra_proportion() == null ? other.getExtra_proportion() == null : this.getExtra_proportion().equals(other.getExtra_proportion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourse_id() == null) ? 0 : getCourse_id().hashCode());
        result = prime * result + ((getUsual_proportion() == null) ? 0 : getUsual_proportion().hashCode());
        result = prime * result + ((getMid_term_proportion() == null) ? 0 : getMid_term_proportion().hashCode());
        result = prime * result + ((getFinal_term_proportion() == null) ? 0 : getFinal_term_proportion().hashCode());
        result = prime * result + ((getExtra_proportion() == null) ? 0 : getExtra_proportion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", course_id=").append(course_id);
        sb.append(", usual_proportion=").append(usual_proportion);
        sb.append(", mid_term_proportion=").append(mid_term_proportion);
        sb.append(", final_term_proportion=").append(final_term_proportion);
        sb.append(", extra_proportion=").append(extra_proportion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}