package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.MajorInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.example.dao.MajorInfo
 */
@Mapper
public interface MajorInfoMapper extends BaseMapper<MajorInfo> {
    //专业名
    List<String> searchAllMajorName();

}




