package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName student_grade
 */
@TableName(value ="student_grade")
@Data
public class StudentGrade implements Serializable {
    /**
     * 
     */
    @TableId(value = "gradeID")
    private Integer gradeID;

    /**
     * 
     */
    @TableField(value = "claName")
    private String claName;

    /**
     * 
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 
     */
    @TableField(value = "stuID")
    private Integer stuID;

    /**
     * 
     */
    @TableField(value = "updateTime")
    private Date updateTime;

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
        StudentGrade other = (StudentGrade) that;
        return (this.getGradeID() == null ? other.getGradeID() == null : this.getGradeID().equals(other.getGradeID()))
            && (this.getClaName() == null ? other.getClaName() == null : this.getClaName().equals(other.getClaName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getStuID() == null ? other.getStuID() == null : this.getStuID().equals(other.getStuID()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGradeID() == null) ? 0 : getGradeID().hashCode());
        result = prime * result + ((getClaName() == null) ? 0 : getClaName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getStuID() == null) ? 0 : getStuID().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gradeID=").append(gradeID);
        sb.append(", claName=").append(claName);
        sb.append(", createTime=").append(createTime);
        sb.append(", score=").append(score);
        sb.append(", stuID=").append(stuID);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}