package com.example.dto;

import com.example.entity.CourseInfoVo;
import com.example.entity.CourseTimeVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTimeVoDTO {
    private int code;
    private String msg;
    private int count;
    private List<CourseTimeVo> data;
}
