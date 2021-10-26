package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.MajorInfoMapper;
import com.example.entity.MajorInfo;
import com.example.service.MajorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class MajorInfoServiceImpl extends ServiceImpl<MajorInfoMapper, MajorInfo>
    implements MajorInfoService{
    @Autowired
    private MajorInfoMapper majorInfoMapper;

    @Override
    public List<String> findAllMajorName() {
        return majorInfoMapper.searchAllMajorName();
    }
}




