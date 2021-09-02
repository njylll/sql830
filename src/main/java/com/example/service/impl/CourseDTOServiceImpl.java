package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CourseInfoMapper;
import com.example.dto.CourseDTO;
import com.example.dto.CourseDetailDTO;
import com.example.entity.CourseDetail;
import com.example.entity.CourseInfo;
import com.example.service.CourseDTOService;
import com.example.service.CourseDetailDTOService;
import com.example.service.CourseInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CourseDTOServiceImpl implements CourseDTOService {

    @Autowired
    private CourseInfoService courseInfoService;

    @Autowired
    private CourseDetailDTOService courseDetailDTOService;

    public CourseDTO queryByCourseID(String courseId)
    {
        CourseDTO courseDTO = new CourseDTO();
        CourseInfo courseInfo = courseInfoService.queryByCourseId(courseId);
        BeanUtils.copyProperties(courseInfo,courseDTO);
        courseDTO.setCourseDetailList(courseDetailDTOService.queryByCourseId(courseId));
        return courseDTO;
    }

    public List<CourseDTO> listAll()
    {
        List<CourseDTO> courseDTOList = new ArrayList<CourseDTO>();
        List<CourseInfo> courseInfoList = courseInfoService.listAll();
        Iterator<CourseInfo> it = courseInfoList.iterator();
        CourseDTO courseDTO = new CourseDTO();
        while (it.hasNext())
        {
            BeanUtils.copyProperties(it,courseDTO);
            courseDTO.setCourseDetailList(courseDetailDTOService.queryByCourseId(it.next().getCourseId()));
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

}
