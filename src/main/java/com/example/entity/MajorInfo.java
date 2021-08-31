package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 专业信息
 * @TableName major_info
 */
@TableName(value ="major_info")
@Data
public class MajorInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "UUID")
    private String UUID;

    /**
     * 专业号
     */
    @TableField(value = "major_id")
    private String major_id;

    /**
     * 专业名称
     */
    @TableField(value = "major_name")
    private String major_name;

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
        MajorInfo other = (MajorInfo) that;
        return (this.getUUID() == null ? other.getUUID() == null : this.getUUID().equals(other.getUUID()))
            && (this.getMajor_id() == null ? other.getMajor_id() == null : this.getMajor_id().equals(other.getMajor_id()))
            && (this.getMajor_name() == null ? other.getMajor_name() == null : this.getMajor_name().equals(other.getMajor_name()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUUID() == null) ? 0 : getUUID().hashCode());
        result = prime * result + ((getMajor_id() == null) ? 0 : getMajor_id().hashCode());
        result = prime * result + ((getMajor_name() == null) ? 0 : getMajor_name().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", UUID=").append(UUID);
        sb.append(", major_id=").append(major_id);
        sb.append(", major_name=").append(major_name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}