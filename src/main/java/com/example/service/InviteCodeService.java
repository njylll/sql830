package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.InviteCode;
import com.example.entity.StudentInfo;

/**
 *
 */
public interface InviteCodeService extends IService<InviteCode> {
    public void save(String code, String role);
}
