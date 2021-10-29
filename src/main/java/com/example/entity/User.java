package com.example.entity;


import com.alibaba.druid.sql.visitor.functions.Now;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Value;
import org.hibernate.sql.Update;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.temporal.ValueRange;

@TableName(value = "user")
@Data
public class User implements Serializable {

  @TableId(value = "UUID")
  private String uuid;
  @TableField(value = "id")
  private String id;
  @TableField(value = "username")
  private String username;
  @TableField(value = "password")
  private String password;
  @TableField(value = "role")
  private String role;
  @TableField(value = "avatar_url")
  private String avatarUrl;
  @TableField(update = "now()")
  private java.sql.Timestamp createTime;
  @TableField(update = "now()")
  private java.sql.Timestamp updateTime;

  private static final long serialVersionUID = 1L;

}



