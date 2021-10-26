package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.ClassInfoMapper;
import com.example.entity.ClassInfo;
import com.example.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements ClassInfoService{

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public List<String> findAllClassId() {
        return classInfoMapper.findAllClassId();
    }
}




