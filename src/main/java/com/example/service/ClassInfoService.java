package com.example.service;

import com.example.entity.ClassInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface ClassInfoService extends IService<ClassInfo> {
    //查询所有班级id
    List<String> findAllClassId();

}
