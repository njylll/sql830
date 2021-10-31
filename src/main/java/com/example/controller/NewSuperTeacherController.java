package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.CourseInfoMapper;
import com.example.dao.UserMapper;
import com.example.dto.CourseModuleDTO;
import com.example.entity.*;
import com.example.service.impl.*;
import javafx.util.converter.LocalDateStringConverter;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/newVersion/superTeacher")
public class NewSuperTeacherController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AliOssServiceImpl aliOssService;
    @Autowired
    private CourseInfoVoServiceImpl courseInfoVoService;
    @Autowired
    private CourseInfoServiceImpl courseInfoService;
    @Autowired
    private CourseInfoMapper courseInfoMapper;
    @Autowired
    private CollegeInfoServiceImpl collegeInfoService;
    @Autowired
    private TeacherInfoServiceImpl teacherInfoService;
    @Autowired
    private CourseDetailServiceImpl courseDetailService;

    @GetMapping(value = {"/","/index"})
    public String toSuperTeacherMainPage(Model model)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        model.addAttribute("avatar",u.getAvatarUrl());
        return "/newVersion/superTeacher/index";
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
        String id;
        if(u!=null)
            id=u.getId();
        else
            id="";
        model.addAttribute("sId",id);
        return "/newVersion/superTeacher/me";
    }
    //个人设置
    @PostMapping("/me")
    @ResponseBody
    public String doEditMe(@RequestParam("username")String username,
                           @RequestParam("studentId")String studentId,
                           @RequestParam("password")String password)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        String originName=u.getUsername();
        if(!StringUtils.isEmpty(username))
            u.setUsername(username);
        if(!StringUtils.isEmpty(studentId))
            u.setId(studentId);
        if(!StringUtils.isEmpty(password))
            u.setPassword(password);
        userService.update(u,new QueryWrapper<User>().eq("username",originName));
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

    //编辑模板页面
    @GetMapping("/editModule")
    public String toEditModulePage()
    {
        return "/newVersion/superTeacher/editModule";
    }
    //模板信息Json
    @GetMapping("/courseModule.json")
    @ResponseBody
    public String getCourseJson()
    {
        List<CourseInfoVo>  courseInfoVos= courseInfoVoService.list();
        CourseModuleDTO courseModuleDTO=new CourseModuleDTO();
        courseModuleDTO.setCode(0);
        courseModuleDTO.setCount(courseInfoVos.size());
        courseModuleDTO.setMsg("成功");
        courseModuleDTO.setData(courseInfoVos);

        return JSON.toJSONString(courseModuleDTO);
    }
    //ajax编辑模板
    @PostMapping("/editCourseModule")
    @ResponseBody
    public String editModule(@RequestParam(value = "originId",required = true)String originId,
                             @RequestParam(value = "courseId",required = false)String courseId,
                             @RequestParam(value = "courseName",required = false)String courseName,
                             @RequestParam(value = "collegeName",required = false)String collegeName,
                             @RequestParam(value = "creditHours",required = false)String creditHours,
                             @RequestParam(value = "credit",required = false)String credit,
                             @RequestParam(value = "courseType",required = false)String courseType)
    {

        if(!StringUtils.isEmpty(courseId))
        {
            courseInfoMapper.updateCourseIdByCourseId(originId,courseId);
            System.out.println(courseId);
        }
        else if(!StringUtils.isEmpty(courseName))
        {
            courseInfoMapper.updateCourseNameByCourseId(originId,courseName);
            System.out.println(courseName);
        }
        else if(!StringUtils.isEmpty(collegeName))
        {
            courseInfoMapper.updateCollegeIdByCourseId(originId,collegeName);
            System.out.println(collegeName);
        }
        else if(!StringUtils.isEmpty(creditHours))
        {
            courseInfoMapper.updateCreditHoursByCourseId(originId,creditHours);
            System.out.println(creditHours);
        }
        else if(!StringUtils.isEmpty(credit))
        {
            courseInfoMapper.updateCreditByCourseId(originId,credit);
            System.out.println(credit);
        }
        else if(!StringUtils.isEmpty(courseType))
        {
            courseInfoMapper.updateCourseTypeByCourseId(originId,courseType);
            System.out.println(courseType);
        }

        return "ok";
    }
    //ajax删除模板
    @PostMapping("/deleteCourseModule")
    @ResponseBody
    public String deleteModule(@RequestParam("courseId")String courseId)
    {
        courseInfoService.remove(new QueryWrapper<CourseInfo>().eq("course_id",courseId));
        return "ok";
    }

    //创建新模板页面
    @GetMapping("/createModule")
    public String toCreateModulePage(Model model)
    {
        List<CollegeInfo> collegeInfos = collegeInfoService.list(new QueryWrapper<CollegeInfo>().select("distinct college_name,college_id"));
        model.addAttribute("collegeInfos",collegeInfos);
        return "/newVersion/superTeacher/createModule";
    }
    //ajax创建新模板
    @PostMapping("/createModule")
    @ResponseBody
    public String doCreateModule(@RequestParam("courseId")String courseId,
                                 @RequestParam("courseName")String courseName,
                                 @RequestParam("collegeId")String collegeId,
                                 @RequestParam("creditHour")int creditHour,
                                 @RequestParam("credit")double credit,
                                 @RequestParam("courseType")String courseType,
                                 HttpServletResponse response)
    {
        //存在相同id
        if(courseInfoService.getOne(new QueryWrapper<CourseInfo>().eq("course_id",courseId))!=null)
        {
            response.setStatus(403);
            return "{\"msg\":\"存在相同id\"}";
        }
        CourseInfo info=new CourseInfo();
        info.setCourseId(courseId);info.setCourseName(courseName);info.setResponsibleCollegeId(collegeId);
        info.setCreditHours((byte) creditHour);
        info.setCredit(credit);
        if (!StringUtils.isEmpty(courseType))
        {
            switch (courseType) {
                case "pcc":
                    info.setCourseType("专业必修课");
                    break;
                case "pe":
                    info.setCourseType("专业选修课");
                    break;
                case "gc":
                    info.setCourseType("通识课");
                    break;
            }
        }
        courseInfoService.save(info);
        return "ok";
    }

    //pcc创建课程界面
    @GetMapping("/pcc")
    public String toPccPage(Model model)
    {
        List<CourseInfoVo> courseInfoVos = courseInfoVoService.list(new QueryWrapper<CourseInfoVo>().eq("course_type","专业必修课"));
        List<TeacherInfo> teacherInfos= teacherInfoService.list();
        model.addAttribute("courseModules",courseInfoVos);
        model.addAttribute("teachers",teacherInfos);
        return "/newVersion/superTeacher/professional_compulsory_course";
    }
    //pe创建课程界面
    @GetMapping("/pe")
    public String toPePage(Model model)
    {
        List<CourseInfoVo> courseInfoVos = courseInfoVoService.list(new QueryWrapper<CourseInfoVo>().eq("course_type","专业选修课"));
        List<TeacherInfo> teacherInfos= teacherInfoService.list();
        model.addAttribute("courseModules",courseInfoVos);
        model.addAttribute("teachers",teacherInfos);
        return "/newVersion/superTeacher/professional_elective";
    }
    //gc创建课程界面
    @GetMapping("/gc")
    public String toGcPage(Model model)
    {
        List<CourseInfoVo> courseInfoVos = courseInfoVoService.list(new QueryWrapper<CourseInfoVo>().eq("course_type","通识课"));
        List<TeacherInfo> teacherInfos= teacherInfoService.list();
        model.addAttribute("courseModules",courseInfoVos);
        model.addAttribute("teachers",teacherInfos);
        return "/newVersion/superTeacher/general_course";
    }
    //ajax创建课程pcc
    @PostMapping("/pcc")
    @ResponseBody
    public String doPccCreate(@RequestParam(value = "courseId",required = false)String courseId,
                              @RequestParam(value = "start",required = false) String start,
                              @RequestParam(value = "end",required = false) String end,
                              @RequestParam(value = "term",required = false)int term,
                              @RequestParam(value = "teacherName",required = false)String teacherName,
                              @RequestParam(value = "location",required = false)String location)
    {
        CourseDetail courseDetail=new CourseDetail();
        courseDetail.setCourseId(courseId);
        courseDetail.setCourseDetailId(RandomString.make(16));
        courseDetail.setCourseCondition("正常");
        courseDetail.setTeacherName(teacherName);
        courseDetail.setStartSchoolYear(LocalDate.parse(start,DateTimeFormatter.ISO_DATE));
        courseDetail.setEndSchoolYear(LocalDate.parse(end,DateTimeFormatter.ISO_DATE));
        courseDetail.setStartTerm((byte) term);
        courseDetail.setTeachingLocation(location);

        courseDetailService.save(courseDetail);

        return "ok";
    }

    private String getUserId()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        return u.getId();
    }
}
