package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.ClassInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.example.dao.ClassInfo
 */
@Mapper
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {
    //查询所有班级id
    List<String> findAllClassId();

}




