package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.UserMapper;
import com.example.entity.User;
import com.example.service.UserAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userAuthenticateService")
public class UserAuthenticateServiceImpl implements UserAuthenticateService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String name= username.split("\\|")[0];
        String role= username.split("\\|")[1];
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",name).or().eq("id",name);
        User u=userMapper.selectOne(wrapper);

        if(u==null)
        {
            throw new UsernameNotFoundException("用户名不存在");
        }
        else if(!u.getRole().equals("ROLE_"+role))
        {
            throw new UsernameNotFoundException("权限不足");
        }

        //添加权限(注意要有ROLE_)
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(u.getRole());
        List<GrantedAuthority> authorities= new ArrayList<>();
        authorities.add(grantedAuthority);
        //返回用户、权限
        return new org.springframework.security.core.userdetails.User(u.getUsername(), new BCryptPasswordEncoder().encode(u.getPassword()),authorities);
    }
}
