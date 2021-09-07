package com.example.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.AdminAccountMapper;
import com.example.entity.AdminAccount;
import com.example.service.AdminAccountService;
import com.example.vo.AdminAccountVO;
import com.example.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAccountServiceImpl  extends ServiceImpl<AdminAccountMapper, AdminAccount> implements AdminAccountService {

    @Autowired
    private AdminAccountMapper adminAccountMapper;

    /*
     * 添加方法
     */
    @Override
    public void add(AdminAccountVO admin) {
        admin.setuserid(UUIDGenerator.generateStudentUUID(admin.getuserid()));
        adminAccountMapper.add(admin);
    }

    /*
     * 更新
     */
    @Override
    public void update(AdminAccountVO admin) {
        adminAccountMapper.update(admin);
    }


    /*
     * 查询全部
     */
    @Override
    public List<AdminAccountVO> queryByAll() {
        return adminAccountMapper.queryByAll();
    }

    /*
     * 查询
     */
    @Override
    public List<AdminAccountVO> queryByOne(String username,String password) {
        return adminAccountMapper.queryByOne(username,password);
    }

    /*
     * 根据ID查询
     */
    @Override
    public AdminAccountVO queryById(String id) {
        return adminAccountMapper.queryById(id);
    }
}

