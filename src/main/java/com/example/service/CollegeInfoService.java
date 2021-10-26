package com.example.service;

import com.example.entity.CollegeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface CollegeInfoService extends IService<CollegeInfo> {
    //学院名
    List<String> findAllCollegeName();

}
