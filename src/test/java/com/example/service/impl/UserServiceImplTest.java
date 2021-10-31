package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.CourseVo;
import com.example.entity.StudentCourse;
import com.example.entity.User;
import com.example.service.CourseVoService;
import com.example.service.StudentCourseService;
import com.example.service.UserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest extends TestCase {

    @Autowired
    public UserService userService;

    @Autowired
    public StudentCourseService studentCourseService;

    @Autowired
    public CourseVoService courseVoService;
    @Test
    public void test() {

            List<CourseVo> courseVoList = courseVoService.list(new QueryWrapper<CourseVo>().select(CourseVo.class, info -> !info.getColumn().equals("course_detail_id")
                    && !info.getColumn().equals("course_type") && !info.getColumn().equals("student_id")).eq("student_id","121212121").eq("course_type","必修课"));

        for(CourseVo courseVo : courseVoList)
        {
            System.out.println(courseVo);
        }
    }

}