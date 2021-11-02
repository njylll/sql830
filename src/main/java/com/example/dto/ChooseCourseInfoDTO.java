package com.example.dto;

import com.example.entity.CourseDetailVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChooseCourseInfoDTO {
    private List<CourseDetailVo> courseNameList;
    private List<CourseDetailVo> courseIdList;
    private List<CourseDetailVo> teacherName ;
    private List<CourseDetailVo> teachingLocation;
}
