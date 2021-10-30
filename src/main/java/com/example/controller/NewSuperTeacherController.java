package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.CourseInfoMapper;
import com.example.dao.UserMapper;
import com.example.dto.CourseModuleDTO;
import com.example.entity.CourseInfo;
import com.example.entity.CourseInfoVo;
import com.example.entity.StudentCourseVo;
import com.example.entity.User;
import com.example.service.impl.*;
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


    private String getUserId()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        return u.getId();
    }
}
