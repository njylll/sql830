package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.CourseVoMapper;
import com.example.dao.StudentCourseVoMapper;
import com.example.dao.StudentInfoMapper;
import com.example.dao.UserMapper;
import com.example.entity.*;
import com.example.service.CourseVoService;
import com.example.service.impl.*;
import com.mysql.cj.xdevapi.JsonParser;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/newVersion/student")
public class NewStudentController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AliOssServiceImpl aliOssService;
    @Autowired
    private StudentInfoServiceImpl studentInfoService;
    @Autowired
    private StudentCourseVoServiceImpl studentCourseVoService;
    @Autowired
    private  CourseVoMapper courseVoMapper;
    @Autowired
    private CourseVoService courseVoService;


    //主页
    @GetMapping(value = {"/","/index"})
    public String toStudentMainPage(Model model)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        model.addAttribute("avatar",u.getAvatarUrl());
        return "/newVersion/student/index";
    }

    //个人设置
    @GetMapping("/me")
    public String toMePage(Model model)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        model.addAttribute("avatar",u.getAvatarUrl());
        model.addAttribute("sId",u.getId());
        StudentInfo studentInfo= studentInfoService.getOne(new QueryWrapper<StudentInfo>().eq("student_id",u.getId()));
        String sName;
        if(studentInfo==null)
            sName="";
        else
            sName=studentInfo.getStudentName();
        model.addAttribute("sName",sName);
        return "/newVersion/student/me";
    }
    //个人设置
    @PostMapping("/me")
    @ResponseBody
    public String doEditMe(@RequestParam("username")String username,
                           @RequestParam("studentId")String studentId,
                           @RequestParam("studentName")String studentName,
                           @RequestParam("password")String password,
                           HttpServletRequest request, HttpServletResponse response)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        String originName=u.getUsername();
        String originId=u.getId();
        boolean hasId=false;
        if(!StringUtils.isEmpty(username))
            u.setUsername(username);
        if(!StringUtils.isEmpty(studentId))
        {
            u.setId(studentId);
            hasId=true;
        }
        if(!StringUtils.isEmpty(password))
            u.setPassword(password);
        //用户表id更新，学生表id同步更新
        System.out.println(u);
        userMapper.update(u,new QueryWrapper<User>().eq("username",originName));
        //学生设置id的情况下，判断学生表有学生
        if(hasId && !StringUtils.isEmpty(studentName))
        {
            StudentInfo newS=new StudentInfo();
            newS.setStudentName(studentName);
            newS.setStudentId(u.getId());//更新后的
            //存在学生记录
            if(studentInfoService.getOne(new QueryWrapper<StudentInfo>().eq("student_id",u.getId()))!=null)
            {
                studentInfoService.update(newS,new QueryWrapper<StudentInfo>().eq("student_id",u.getId()));
            }
            else
            {
                studentInfoService.save(newS);
            }
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {//清除认证
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "{\"message\":修改成功，请重新登陆}";
    }

    //上传头像
    @PostMapping("/me/avatar")
    @ResponseBody
    public String upLoadAvatar(@RequestParam("file") MultipartFile file) throws IOException
    {
        if (file.isEmpty()) {
            return "{" +
                    "  \"code\": 1\n" +
                    "  ,\"msg\": \"上传空文件\"\n" +
                    "  ,\"data\": {\n" +
                    "    \"src\": \"\"\n" +
                    "  }" +
                    "}       ";
        }
        String fileName= RandomString.make(16);
        String imgUrl="avatar/"+fileName+".jpg";
        aliOssService.uploadImg(imgUrl,file.getInputStream());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        u.setAvatarUrl("https://sunnynoodlebucket.oss-cn-shanghai.aliyuncs.com/"+imgUrl);
        userService.update(u,new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        return "{" +
                "  \"code\": 0\n" +
                "  ,\"msg\": \"成功\"\n" +
                "  ,\"data\": {\n" +
                "    \"src\": \"https://sunnynoodlebucket.oss-cn-shanghai.aliyuncs.com/avatar/"+ fileName+".jpg" +".jpg\"" +
                "  }" +
                "}       ";
    }

    //课表
    @GetMapping("/curriculum")
    public String toCurriculumPage(Model model)
    {
        String uId=getUserId();
        List<StudentCourseVo> courseList= studentCourseVoService.list(new QueryWrapper<StudentCourseVo>().eq("student_id",uId).orderByAsc("day_time"));
        String courseJson= JSON.toJSONString(courseList);
        model.addAttribute("courses",courseJson);
        //System.out.println(courseJson);
        return "/newVersion/student/curriculum";
    }

    private String getUserId()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        return u.getId();
    }
    @PostMapping("/newVersion/student/pcc")
    public String searchCourse(CourseVo courseVo,
                               Model model)
    {
        QueryWrapper<CourseVo> courseVoQueryWrapper=new QueryWrapper<>();
        Map<String,String> eqMap=new HashMap<>();

        if(!StringUtils.isEmpty(courseVo.getCourseId()))
            eqMap.put("course_id",courseVo.getCourseId());

        if(!StringUtils.isEmpty(courseVo.getCourseName()))
            eqMap.put("course_name",courseVo.getCourseName());

        if(!StringUtils.isEmpty(courseVo.getTeacherName())){

            eqMap.put("teacher_name",courseVo.getTeacherName());
        }

        if(!StringUtils.isEmpty(courseVo.getTeachingLocation())){

            eqMap.put("teaching_location",courseVo.getTeachingLocation());
        }
        if(!StringUtils.isEmpty(courseVo.getAssessmentMethod())){

            eqMap.put("assessment_method",courseVo.getAssessmentMethod());
        }
        if(!StringUtils.isEmpty(courseVo.getCourseType())){

            eqMap.put("course_type",courseVo.getCourseType());
        }
        if(!StringUtils.isEmpty(courseVo.getCourseDetailId())){

            eqMap.put("course_detail_id",courseVo.getCourseDetailId());
        }




        courseVoQueryWrapper.allEq(eqMap);
        List<CourseVo> courseVoList = courseVoService.list(courseVoQueryWrapper);
        model.addAttribute("courseVoList",courseVoList);

        List<String> courseNameList = courseVoMapper.searchAllCourseName();
        model.addAttribute("cName",courseNameList);
        List<String> idList= courseVoMapper.listAll();
        model.addAttribute("cId",idList);
        List<CourseVo> startSchoolYear = courseVoService.list(new QueryWrapper<CourseVo>().select("distinct startSchoolYear").orderByAsc("startSchoolYear"));
        model.addAttribute("startschoolyear",startSchoolYear);
        List<CourseVo> endSchoolYear = courseVoService.list(new QueryWrapper<CourseVo>().select("distinct endSchoolYear"));
        model.addAttribute("endschoolyear",endSchoolYear);
        List<CourseVo> teachername = courseVoService.list(new QueryWrapper<CourseVo>().select("distinct teacher_name").orderByAsc("teacher_name"));
        model.addAttribute("teacher_name",teachername);
        List<CourseVo> teachingLocation = courseVoService.list(new QueryWrapper<CourseVo>().select("distinct teaching_location"));
        model.addAttribute("teaching_location",teachingLocation);
        List<CourseVo> assessment_method = courseVoService.list(new QueryWrapper<CourseVo>().select("distinct assessment_method"));
        model.addAttribute("assessment_method",assessment_method);



        return "newVersion/student/courseInfoList";
    }

}
