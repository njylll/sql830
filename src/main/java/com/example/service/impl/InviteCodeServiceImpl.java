package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.InviteCode;
import com.example.service.InviteCodeService;
import com.example.dao.InviteCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class InviteCodeServiceImpl extends ServiceImpl<InviteCodeMapper, InviteCode>
    implements InviteCodeService{

    @Autowired
    private InviteCodeMapper inviteCodeMapper;
    public void save(String code, String role)
    {
        InviteCode inviteCode = new InviteCode();
        inviteCode.setCode(code);
        inviteCode.setRole(role);
        inviteCode.setUsed((byte)0);
        inviteCodeMapper.insert(inviteCode);
    }
}




