package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    private Integer gradeid;

    /**
     * 
     */
    @TableField(value = "claName")
    private String claname;

    /**
     * 
     */
    @TableField(value = "createTime")
    private Date createtime;

    /**
     * 
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 
     */
    @TableField(value = "stuID")
    private Integer stuid;

    /**
     * 
     */
    @TableField(value = "updateTime")
    private Date updatetime;

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
        return (this.getGradeid() == null ? other.getGradeid() == null : this.getGradeid().equals(other.getGradeid()))
            && (this.getClaname() == null ? other.getClaname() == null : this.getClaname().equals(other.getClaname()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getStuid() == null ? other.getStuid() == null : this.getStuid().equals(other.getStuid()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGradeid() == null) ? 0 : getGradeid().hashCode());
        result = prime * result + ((getClaname() == null) ? 0 : getClaname().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getStuid() == null) ? 0 : getStuid().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gradeid=").append(gradeid);
        sb.append(", claname=").append(claname);
        sb.append(", createtime=").append(createtime);
        sb.append(", score=").append(score);
        sb.append(", stuid=").append(stuid);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}