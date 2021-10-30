package com.example.dto;

import com.example.entity.CourseInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseModuleDTO {
    private int code;
    private String msg;
    private int count;
    private List<CourseInfoVo> data;

}
