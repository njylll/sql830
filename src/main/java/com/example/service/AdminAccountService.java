package com.example.service;

import com.example.entity.AdminAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.AdminAccountVO;

import java.util.List;

/**
 *
 */
public interface AdminAccountService extends IService<AdminAccount> {
    void add(AdminAccountVO adminAccountVO);

    void update(AdminAccountVO adminAccountVO);

    List<AdminAccountVO> queryByAll();

    List<AdminAccountVO> queryByOne(String username, String password);

    AdminAccountVO queryById(String UUID);
}

