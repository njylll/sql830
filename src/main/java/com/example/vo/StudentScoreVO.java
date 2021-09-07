package com.example.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentScoreVO implements Serializable{
    private String studentId;

    private String courseName;

    private Float usualScore;

    private Float midTermScore;

    private Float finalTermScore;
}
