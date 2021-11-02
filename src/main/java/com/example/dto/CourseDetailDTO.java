package com.example.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CourseDetailDTO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String courseId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startSchoolYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endSchoolYear;

    private Byte startTerm;
    private Byte creditHours;
    /**
     * 学分
     */
    private Double credit;



}
