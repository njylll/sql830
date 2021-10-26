package com.example.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.DemoApplication;
import com.example.entity.*;
import com.example.service.MajorInfoService;
import com.example.service.impl.*;
import com.example.util.UUIDGenerator;
import com.example.vo.StudentInfoVO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    public static final Logger log = LoggerFactory.getLogger(DemoApplication.class);


    @Autowired
    private StudentInfoServiceImpl studentInfoService;

    @Autowired
    private StudentVoServiceImpl studentVoService;

    @Autowired
    private ClassInfoServiceImpl classInfoService;

    @Autowired
    private CollegeInfoServiceImpl collegeInfoService;

    @Autowired
    private MajorInfoServiceImpl majorInfoService;

    //    用户登录页面
    @RequestMapping(value = "/dashboard")
    public String entry() {
        return "dashboard";
    }

    //查询所有学生返回列表页面
    @GetMapping("/student/studentList")
    public String getStudents(Model model)
    {
        List<StudentVo> studentInfoVOS = studentVoService.list();
        List<String> idList= studentInfoService.findAllStudentId();
        List<String> nameList= studentInfoService.findAllStudentName();
        List<String> classList= classInfoService.findAllClassId();
        List<String> collegeList= collegeInfoService.findAllCollegeName();
        List<String> majorList= majorInfoService.findAllMajorName();
        model.addAttribute("sId",idList);
        model.addAttribute("sName",nameList);
        model.addAttribute("classId",classList);
        model.addAttribute("collegeName",collegeList);
        model.addAttribute("studentInfos", studentInfoVOS);
        model.addAttribute("majorName",majorList);

        return "/student/list";
    }

    //查询学生
    @PostMapping("/student/search")
    public String searchStu(StudentVo studentInfo,
                            Model model)
    {
        QueryWrapper<StudentVo> studentInfoQueryWrapper=new QueryWrapper<>();
        Map<String,String> eqMap=new HashMap<>();

        //判断学号
        if(!StringUtils.isEmpty(studentInfo.getStudentId()))
            eqMap.put("student_id",studentInfo.getStudentId());
        //判断姓名
        if(!StringUtils.isEmpty(studentInfo.getStudentName()))
            eqMap.put("student_name",studentInfo.getStudentName());
        //判断性别
        if(!StringUtils.isEmpty(studentInfo.getGender()))
        {
            if (studentInfo.getGender().equals("男"))
                eqMap.put("gender","0");
            else
                eqMap.put("gender","1");
        }
        //判断班级
        if(!StringUtils.isEmpty(studentInfo.getClassId()))
            eqMap.put("class_id",studentInfo.getClassId());
        //查询是否有指定学院名称的学院
        if(!StringUtils.isEmpty(studentInfo.getCollegeName()))
        {
            eqMap.put("college_name",studentInfo.getCollegeName());
        }
        //查询是否有指定专业名称的专业
        if(!StringUtils.isEmpty(studentInfo.getMajorName()))
        {
            eqMap.put("major_name",studentInfo.getMajorName());
        }
        //判断学制
        if(studentInfo.getSchoolingPeriod()!=null)
            eqMap.put("schooling_period",studentInfo.getSchoolingPeriod().toString());

        studentInfoQueryWrapper.allEq(eqMap);
        List<StudentVo> studentVos =studentVoService.list(studentInfoQueryWrapper);
        model.addAttribute("studentInfos",studentVos);
        List<String> nameList= studentInfoService.findAllStudentName();
        List<String> idList= studentInfoService.findAllStudentId();
        List<String> classList= classInfoService.findAllClassId();
        List<String> collegeList= collegeInfoService.findAllCollegeName();
        List<String> majorList= majorInfoService.findAllMajorName();
        model.addAttribute("sName",nameList);
        model.addAttribute("sId",idList);
        model.addAttribute("classId",classList);
        model.addAttribute("collegeName",collegeList);
        model.addAttribute("majorName",majorList);
        return "/student/list";
    }

    //get来到学生添加页面
    @GetMapping("/student/addStudent")
    public String toAddPage(Model model)
    {
        List<String> classList= classInfoService.findAllClassId();
        List<String> collegeList= collegeInfoService.findAllCollegeName();
        List<String> majorList= majorInfoService.findAllMajorName();
        model.addAttribute("classId",classList);
        model.addAttribute("collegeName",collegeList);
        model.addAttribute("majorName",majorList);
        return "/student/add";
    }

    //学生添加
    @PostMapping("/student/doAdd")
    public String addStu(StudentVo student) {
        String uuid= UUIDGenerator.generateStudentUUID(student.getStudentId());
        studentVoService.insertStudent(student,uuid);

        return "redirect:/student/studentList";
    }

    //学生信息修改页面
    @GetMapping("/student/update/{studentId}")
    public String toEditPage(@PathVariable("studentId") String studentId
                            ,Model model)
    {
        //查询要修改的id
        QueryWrapper<StudentVo> voQueryWrapper=new QueryWrapper<>();
        voQueryWrapper.eq("student_id",studentId);
        StudentVo vo = studentVoService.getOne(voQueryWrapper);
        model.addAttribute("student",vo);

        return "student/change";
    }

    //提交修改
    @PostMapping(value = {"/student/doUpdate"})
    public String putStudent(StudentVo vo) {
        String studentId=vo.getStudentId();

        studentInfoService.updateStudentInfo(vo);

        return "redirect:/student/update/"+studentId;

    }

    @DeleteMapping("/student/{studentId}")
    public String deleteStu(@PathVariable("studentId") String studentId)
    {
        QueryWrapper<StudentInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        studentInfoService.remove(queryWrapper);

        return "redirect:/student/studentList";

    }

}
