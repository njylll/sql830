package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CollegeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.example.dao.CollegeInfo
 */
@Mapper
public interface CollegeInfoMapper extends BaseMapper<CollegeInfo> {
    //学院名
    List<String> searchAllCollegeName();

}




