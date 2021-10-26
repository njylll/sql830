package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CollegeInfoMapper;
import com.example.entity.CollegeInfo;
import com.example.service.CollegeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class CollegeInfoServiceImpl extends ServiceImpl<CollegeInfoMapper, CollegeInfo>
    implements CollegeInfoService{
    @Autowired
    private CollegeInfoMapper collegeInfoMapper;

    @Override
    public List<String> findAllCollegeName() {
        return collegeInfoMapper.searchAllCollegeName();
    }
}




