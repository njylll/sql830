package com.example.dto;

import com.example.entity.CourseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVoDTO {
    private int code;
    private String msg;
    private int count;
    private List<CourseVo> data;
}
