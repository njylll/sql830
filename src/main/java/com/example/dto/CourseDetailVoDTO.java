package com.example.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.entity.CourseDetailDTO;
import com.example.entity.CourseDetailVo;
import com.example.entity.CourseInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailVoDTO
{
    private int code;
    private String msg;
    private int count;
    private List<CourseDetailVo> data;


}
