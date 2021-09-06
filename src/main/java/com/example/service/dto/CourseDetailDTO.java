package com.example.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailDTO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String courseId;
    private String courseDetailId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startSchoolYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endSchoolYear;

    private Byte startTerm;
    private String courseCondition;
    private String teacherName;
    private String teachingLocation;

    /**
     * 课程时间
     */
    private Byte startWeek;
    private Byte endWeek;
    private String dayTime;
    private String section;
}
