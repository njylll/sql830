package com.example;

import com.example.dao.StudentInfoMapper;
import com.example.entity.StudentInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    public StudentInfoMapper studentInfoMapper;

Calendar calendar=Calendar.getInstance();
    @Test
    public void contextLoads() {
    }
@Autowired
    ApplicationContext ioc;

    @Test
    public void testStudengtRepo() {
    boolean b=ioc.containsBean("StudentRepository");
            System.out.println("存在倉庫bean："+b);
    }
    @Before
    public void setUp() throws Exception {
//                StudentBean stu1=new StudentBean(2, "Jack", "男", 14, calendar.getTime(), calendar.getTime());

    }
    @Test
    public void existStudengtBean() {
        boolean b=ioc.containsBean("StudentBean");
        System.out.println("存在数据bean："+b);
    }

    @Test
    public void t1()
    {
       StudentInfo info= studentInfoMapper.selectById("412");
        System.out.println(info);
    }
//@Test
//public void getController{
//        LoginController login=LoginController.getStudent(2);
//    }
}
