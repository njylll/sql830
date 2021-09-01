package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.example..StudentVo
 */
@Mapper
public interface StudentVoMapper extends BaseMapper<StudentVo> {
    void insertStudentInfo(@Param("vo") StudentVo vo,@Param("UUID")String UUID);
}




