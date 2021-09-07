package com.example.controller;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.CourseInfo;
import com.example.entity.StudentInfo;
import com.example.entity.StudentScore;
import com.example.entity.StudentVo;
import com.example.service.CourseInfoService;
import com.example.service.StudentScoreService;
import com.example.service.impl.CourseInfoServiceImpl;
import com.example.service.impl.StudentScoreServiceImpl;
import com.example.util.UUIDGenerator;
import com.example.vo.StudentScoreVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  控制层
 */
@Controller
class ScoreController {


    @Autowired(required=false)
    private StudentScoreServiceImpl scoreService;

    @Autowired(required=false)
    private CourseInfoServiceImpl courseService;


    //查询
    @GetMapping("/score/scoreList")
    public String getStudentSores(Model model)
    {
        List<StudentScore> scoreList = scoreService.listAll();
        List<StudentScoreVO> studentScoreVOS=new ArrayList<>();
        for (StudentScore studentScore : scoreList) {
            StudentScoreVO vo=new StudentScoreVO();
            BeanUtils.copyProperties(studentScore,vo);
            QueryWrapper<CourseInfo> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("course_id",studentScore.getCourseId());
            vo.setCourseName(courseService.getOne(queryWrapper).getCourseName());
            studentScoreVOS.add(vo);
        }
        model.addAttribute("scoreList", studentScoreVOS);

        return "/score/scoreList";
    }

    //查询
    @PostMapping("/score/search")
    public String searchStuScore(StudentScoreVO studentScore,
                            Model model)
    {
        QueryWrapper<StudentScore> studentScoreQueryWrapper=new QueryWrapper<>();
        Map<String,String> eqMap=new HashMap<>();

        //判断学号
        if(!StringUtils.isEmpty(studentScore.getStudentId()))
            eqMap.put("student_id",studentScore.getStudentId());

        if(!StringUtils.isEmpty(studentScore.getCourseName()))
        {
            QueryWrapper<CourseInfo> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("course_name",studentScore.getCourseName());
            CourseInfo courseInfo=courseService.getOne(queryWrapper);
            if(courseInfo!=null)
            {
                String courseId=courseInfo.getCourseId();
                    if(courseId!=null)
                        eqMap.put("course_id",courseId);
            }
        }

        studentScoreQueryWrapper.allEq(eqMap);
        List<StudentScore> studentScores =scoreService.list(studentScoreQueryWrapper);
        List<StudentScoreVO> studentScoreVOS=new ArrayList<>();
        for (StudentScore score : studentScores) {
            StudentScoreVO vo=new StudentScoreVO();
            BeanUtils.copyProperties(score,vo);
            QueryWrapper<CourseInfo> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("course_id",score.getCourseId());
            vo.setCourseName(courseService.getOne(queryWrapper).getCourseName());
            studentScoreVOS.add(vo);
        }

        model.addAttribute("scoreList",studentScoreVOS);

        return "/score/scoreList";
    }

    /*
     * 添加
     */
    @GetMapping("/score/add")
    public String toAddScorePage()
    {
        return "/score/addScore";
    }


    @PostMapping("/score/doAdd")
    public String addStudentScore(StudentScoreVO studentScoreVO)
    {
        StudentScore newScore=new StudentScore();
        BeanUtils.copyProperties(studentScoreVO,newScore);
        QueryWrapper<CourseInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_name",studentScoreVO.getCourseName());

        newScore.setCourseId(courseService.getOne(queryWrapper).getCourseId());

        scoreService.add(newScore);
        return "redirect:/score/scoreList";
    }


    //学生分数修改页面
    @GetMapping("/score/update/{studentId}/{courseName}")
    public String toEditScorePage(@PathVariable("studentId") String studentId
                                  ,@PathVariable("courseName") String courseName
                                  ,Model model)
    {
        //查询要修改的id
        QueryWrapper<CourseInfo> courseInfoQueryWrapper=new QueryWrapper<>();
        courseInfoQueryWrapper.eq("course_name",courseName);
        String courseId=courseService.getOne(courseInfoQueryWrapper).getCourseId();

        QueryWrapper<StudentScore> scoreQueryWrapper=new QueryWrapper<>();
        scoreQueryWrapper.eq("student_id",studentId);
        scoreQueryWrapper.eq("course_id",courseId);
        StudentScore studentScore = scoreService.getOne(scoreQueryWrapper);

        StudentScoreVO vo=new StudentScoreVO();
        BeanUtils.copyProperties(studentScore,vo);
        vo.setCourseName(courseName);

        model.addAttribute("score",vo);

        return "/score/changeScore";
    }

    //提交修改
    @PostMapping(value = {"/score/doUpdate"})
    public String putScore(StudentScoreVO vo) {
        String studentId=vo.getStudentId();
        String courseName= vo.getCourseName();

        QueryWrapper<CourseInfo> courseInfoQueryWrapper=new QueryWrapper<>();
        courseInfoQueryWrapper.eq("course_name",courseName);
        StudentScore studentScore=new StudentScore();
        BeanUtils.copyProperties(vo,studentScore);
        studentScore.setCourseId(courseService.getOne(courseInfoQueryWrapper).getCourseId());


        scoreService.update(studentScore);
        String code = "/score/scoreList";
        try {
            code= URLEncoder.encode(courseName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/score/update/"+studentId+"/"+code;

    }

}