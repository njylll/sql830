package com.example.service;

import com.example.dto.CourseDetailDTO;
import com.example.entity.CourseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface CourseDetailDTOService
{
    /**
     * 查询结果包括课程时间
     * @param id
     * @return
     */
    public List<CourseDetailDTO> queryByCourseId(String id);

    public List<CourseDetailDTO> listAll();
    /**
     * 添加课程详情,包括时间
     */

    public void add(CourseDetailDTO courseDetailDTO);

    public CourseDetailDTO queryByCourseDetailId(String detailId);

    public void update(CourseDetailDTO courseDetailDTO);

    public void remove(String courseDetailId);

}
