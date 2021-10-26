package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.User;
import com.example.service.UserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest extends TestCase {

    @Autowired
    public UserService userService;
    @Test
    public void test()
    {

        Map<String,String> mmap = new HashMap<String,String>();
        mmap.put("username","njy");
        mmap.put("password","123");
        User user = userService.getOne(new QueryWrapper<User>().allEq(mmap));
        if(user != null)
        {
            System.out.println("找到了");
        }
        else {
            System.out.println("没找到");
        }
    }

}