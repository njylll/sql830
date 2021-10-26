package com.example.service;

import com.example.entity.MajorInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface MajorInfoService extends IService<MajorInfo> {
    List<String> findAllMajorName();

}
