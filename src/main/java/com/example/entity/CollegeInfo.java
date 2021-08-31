package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

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
    private String UUID;

    /**
     * 学院id
     */
    @TableField(value = "college_id")
    private String college_id;

    /**
     * 学院名
     */
    @TableField(value = "college_name")
    private String college_name;

    /**
     * 院长
     */
    @TableField(value = "college_principal")
    private String college_principal;

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
        return (this.getUUID() == null ? other.getUUID() == null : this.getUUID().equals(other.getUUID()))
            && (this.getCollege_id() == null ? other.getCollege_id() == null : this.getCollege_id().equals(other.getCollege_id()))
            && (this.getCollege_name() == null ? other.getCollege_name() == null : this.getCollege_name().equals(other.getCollege_name()))
            && (this.getCollege_principal() == null ? other.getCollege_principal() == null : this.getCollege_principal().equals(other.getCollege_principal()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUUID() == null) ? 0 : getUUID().hashCode());
        result = prime * result + ((getCollege_id() == null) ? 0 : getCollege_id().hashCode());
        result = prime * result + ((getCollege_name() == null) ? 0 : getCollege_name().hashCode());
        result = prime * result + ((getCollege_principal() == null) ? 0 : getCollege_principal().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", UUID=").append(UUID);
        sb.append(", college_id=").append(college_id);
        sb.append(", college_name=").append(college_name);
        sb.append(", college_principal=").append(college_principal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}