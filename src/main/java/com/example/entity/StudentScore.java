package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 学生成绩
 * @TableName student_score
 */
@TableName(value ="student_score")
@Data
public class StudentScore implements Serializable {
    /**
     * 学生学号
     */
    @TableField(value = "student_id")
    private String student_id;

    /**
     * 课程号
     */
    @TableField(value = "course_id")
    private String course_id;

    /**
     * 平时成绩
     */
    @TableField(value = "usual_score")
    private Double usual_score;

    /**
     * 期中成绩
     */
    @TableField(value = "mid_term_score")
    private Double mid_term_score;

    /**
     * 期末成绩
     */
    @TableField(value = "final_term_score")
    private Double final_term_score;

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
        StudentScore other = (StudentScore) that;
        return (this.getStudent_id() == null ? other.getStudent_id() == null : this.getStudent_id().equals(other.getStudent_id()))
            && (this.getCourse_id() == null ? other.getCourse_id() == null : this.getCourse_id().equals(other.getCourse_id()))
            && (this.getUsual_score() == null ? other.getUsual_score() == null : this.getUsual_score().equals(other.getUsual_score()))
            && (this.getMid_term_score() == null ? other.getMid_term_score() == null : this.getMid_term_score().equals(other.getMid_term_score()))
            && (this.getFinal_term_score() == null ? other.getFinal_term_score() == null : this.getFinal_term_score().equals(other.getFinal_term_score()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStudent_id() == null) ? 0 : getStudent_id().hashCode());
        result = prime * result + ((getCourse_id() == null) ? 0 : getCourse_id().hashCode());
        result = prime * result + ((getUsual_score() == null) ? 0 : getUsual_score().hashCode());
        result = prime * result + ((getMid_term_score() == null) ? 0 : getMid_term_score().hashCode());
        result = prime * result + ((getFinal_term_score() == null) ? 0 : getFinal_term_score().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", student_id=").append(student_id);
        sb.append(", course_id=").append(course_id);
        sb.append(", usual_score=").append(usual_score);
        sb.append(", mid_term_score=").append(mid_term_score);
        sb.append(", final_term_score=").append(final_term_score);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}