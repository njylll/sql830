package com.example.service.impl;

import com.example.dto.CourseDTO;
import com.example.service.CourseDTOService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseDTOServiceImplTest extends TestCase {

    @Autowired
    private CourseDTOService courseDTOService;

    @Test
    public void testQueryByCourseID() {
        CourseDTO courseDTO = courseDTOService.queryByCourseID("1");
        System.out.println(courseDTO);
    }

    public void testListAll() {
    }
}