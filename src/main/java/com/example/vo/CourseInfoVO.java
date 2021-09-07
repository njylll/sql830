package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfoVO {
    private String uuid;

    private String courseId;

    private String courseName;

    private String responsibleCollegeName;

    private Byte creditHours;

    private Double credit;

    private String courseType;

    private String courseType2;

    private String teachingWay;

    private String assessmentMethod;
}
