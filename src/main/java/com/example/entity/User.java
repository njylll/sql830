package com.example.entity;


import com.alibaba.druid.sql.visitor.functions.Now;
import com.baomidou.mybatisplus.annotation.TableField;
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

  @Id
  private String uuid;
  private String id;
  private String username;
  private String password;
  private String role;
  @TableField(update = "now()")
  private java.sql.Timestamp createTime;
  @TableField(update = "now()")
  private java.sql.Timestamp updateTime;

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;

}



