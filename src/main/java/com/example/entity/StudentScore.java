package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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
    private String studentId;

    /**
     * 课程号
     */
    @TableField(value = "course_id")
    private String courseId;

    /**
     * 成绩
     */
    @TableField(value = "usual_score")
    private Float usualScore;

    @TableField(value = "mid_term_score")
    private Float midTermScore;

    @TableField(value = "final_term_score")
    private Float finalTermScore;


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
        return (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getUsualScore() == null ? other.getUsualScore() == null : this.getUsualScore().equals(other.getUsualScore()))
                && (this.getMidTermScore() == null ? other.getMidTermScore() == null : this.getMidTermScore().equals(other.getMidTermScore()))
                && (this.getFinalTermScore() == null ? other.getFinalTermScore() == null : this.getFinalTermScore().equals(other.getFinalTermScore()));
   }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getUsualScore() == null) ? 0 : getUsualScore().hashCode());
        result = prime * result + ((getMidTermScore() == null) ? 0 : getMidTermScore().hashCode());
        result = prime * result + ((getFinalTermScore() == null) ? 0 : getFinalTermScore().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", studentId=").append(studentId);
        sb.append(", courseId=").append(courseId);
        sb.append(", usualScore=").append(usualScore);
        sb.append(", midTermScore=").append(midTermScore);
        sb.append(", finalScore=").append(finalTermScore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}