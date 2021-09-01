package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 学院信息
 * @TableName college_info
 */
@TableName(value ="college_info")
@Data
public class CollegeInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "UUID")
    private String uuid;

    /**
     * 学院id
     */
    @TableField(value = "college_id")
    private String collegeId;

    /**
     * 学院名
     */
    @TableField(value = "college_name")
    private String collegeName;

    /**
     * 院长
     */
    @TableField(value = "college_principal")
    private String collegePrincipal;

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
        CollegeInfo other = (CollegeInfo) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getCollegeId() == null ? other.getCollegeId() == null : this.getCollegeId().equals(other.getCollegeId()))
            && (this.getCollegeName() == null ? other.getCollegeName() == null : this.getCollegeName().equals(other.getCollegeName()))
            && (this.getCollegePrincipal() == null ? other.getCollegePrincipal() == null : this.getCollegePrincipal().equals(other.getCollegePrincipal()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getCollegeId() == null) ? 0 : getCollegeId().hashCode());
        result = prime * result + ((getCollegeName() == null) ? 0 : getCollegeName().hashCode());
        result = prime * result + ((getCollegePrincipal() == null) ? 0 : getCollegePrincipal().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", collegeId=").append(collegeId);
        sb.append(", collegeName=").append(collegeName);
        sb.append(", collegePrincipal=").append(collegePrincipal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}