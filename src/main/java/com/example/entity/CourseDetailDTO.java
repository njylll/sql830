package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {
    private int code;
    private String msg;
    private int count;
    private List<CourseDetailVo> data;
}
