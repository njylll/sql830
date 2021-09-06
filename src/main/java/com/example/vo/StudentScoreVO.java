package com.example.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class StudentScoreVO implements Serializable{
    private String studentId;
    private String courseId;
    private String score;
}
