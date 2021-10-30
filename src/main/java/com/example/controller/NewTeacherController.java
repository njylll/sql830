package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.UserMapper;
import com.example.entity.StudentCourseVo;
import com.example.entity.StudentInfo;
import com.example.entity.TeacherInfo;
import com.example.entity.User;
import com.example.service.impl.AliOssServiceImpl;
import com.example.service.impl.StudentCourseVoServiceImpl;
import com.example.service.impl.TeacherInfoServiceImpl;
import com.example.service.impl.UserServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.List;

@Controller
@RequestMapping("/newVersion/teacher")
public class NewTeacherController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AliOssServiceImpl aliOssService;
    @Autowired
    private StudentCourseVoServiceImpl studentCourseVoService;
    @Autowired
    private TeacherInfoServiceImpl teacherInfoService;

    //主页
    @GetMapping(value = {"/","/index"})
    public String toTeacherMainPage(Model model)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        model.addAttribute("avatar",u.getAvatarUrl());
        return "/newVersion/teacher/index";
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
        return "/newVersion/teacher/me";
    }
    //个人设置
    @PostMapping("/me")
    @ResponseBody
    public String doEditMe(@RequestParam("username")String username,
                           @RequestParam("studentId")String studentId,
                           @RequestParam("teacherName")String teacherName,
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
        userMapper.update(u,new QueryWrapper<User>().eq("username",originName));
        //学生设置id的情况下，判断学生表有学生
        if(hasId && !StringUtils.isEmpty(teacherName))
        {
            TeacherInfo newS=new TeacherInfo();
            newS.setTeacherName(teacherName);
            newS.setTeacherId(u.getId());//更新后的
            //存在学生记录
            if(teacherInfoService.getOne(new QueryWrapper<TeacherInfo>().eq("teacher_id",u.getId()))!=null)
            {
                teacherInfoService.update(newS,new QueryWrapper<TeacherInfo>().eq("teacher_id",u.getId()));
            }
            else
            {
                teacherInfoService.save(newS);
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
        List<StudentCourseVo> courseList= studentCourseVoService.list(new QueryWrapper<StudentCourseVo>().eq("teacher_id",uId).orderByAsc("day_time"));
        String courseJson= JSON.toJSONString(courseList);
        model.addAttribute("courses",courseJson);
        //System.out.println(courseJson);
        return "/newVersion/teacher/curriculum";
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
