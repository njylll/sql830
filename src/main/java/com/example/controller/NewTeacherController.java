package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.UserMapper;
import com.example.dto.CourseDetailDTO;
import com.example.dto.CourseVoDTO;
import com.example.entity.*;
import com.example.service.CourseDetailService;
import com.example.service.CourseInfoService;
import com.example.service.CourseVoService;
import com.example.service.impl.AliOssServiceImpl;
import com.example.service.impl.StudentCourseVoServiceImpl;
import com.example.service.impl.TeacherInfoServiceImpl;
import com.example.service.impl.UserServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
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
    private CourseVoService courseVoService;

    @Autowired
    private CourseDetailService courseDetailService;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private TeacherInfoServiceImpl teacherInfoService;
    //主页
    @GetMapping(value = {"/", "/index"})
    public String toTeacherMainPage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("username", userDetails.getUsername()));
        model.addAttribute("avatar", u.getAvatarUrl());
        return "/newVersion/teacher/index";
    }

    //个人设置
    @GetMapping("/me")
    public String toMePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("username", userDetails.getUsername()));
        model.addAttribute("avatar", u.getAvatarUrl());
        String id;
        if (u != null)
            id = u.getId();
        else
            id = "";
        if(!StringUtils.isEmpty(id))
        {
            String tName= teacherInfoService.getOne(new QueryWrapper<TeacherInfo>().eq("teacher_id",id)).getTeacherName();
            model.addAttribute("tName", tName);
        }
        model.addAttribute("sId", id);
        return "/newVersion/teacher/me";
    }

    //个人设置
    @PostMapping("/me")
    @ResponseBody
    public String doEditMe(@RequestParam("username") String username,
                           @RequestParam("teacherId") String teacherId,
                           @RequestParam("teacherName")String teacherName,
                           @RequestParam("password") String password,
                            HttpServletResponse response,
                           HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("username", userDetails.getUsername()));
        String originName = u.getUsername();
        if (!StringUtils.isEmpty(username))
            u.setUsername(username);
        //相当于取消绑定
        u.setId(teacherId);
        if (!StringUtils.isEmpty(password))
            u.setPassword(password);
        userService.update(u, new QueryWrapper<User>().eq("username", originName));
        //绑定老师
        if(!StringUtils.isEmpty(teacherId))
        {
            TeacherInfo teacherInfo=new TeacherInfo();
            teacherInfo.setTeacherId(teacherId);
            teacherInfo.setTeacherName(teacherName);
            teacherInfoService.saveOrUpdate(teacherInfo,new QueryWrapper<TeacherInfo>().eq("teacher_id",teacherId));
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
    public String upLoadAvatar(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "{" +
                    "  \"code\": 1\n" +
                    "  ,\"msg\": \"上传空文件\"\n" +
                    "  ,\"data\": {\n" +
                    "    \"src\": \"\"\n" +
                    "  }" +
                    "}       ";
        }
        String fileName = RandomString.make(16);
        String imgUrl = "avatar/" + fileName + ".jpg";
        aliOssService.uploadImg(imgUrl, file.getInputStream());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("username", userDetails.getUsername()));
        u.setAvatarUrl("https://sunnynoodlebucket.oss-cn-shanghai.aliyuncs.com/" + imgUrl);
        userService.update(u, new QueryWrapper<User>().eq("username", userDetails.getUsername()));
        return "{" +
                "  \"code\": 0\n" +
                "  ,\"msg\": \"成功\"\n" +
                "  ,\"data\": {\n" +
                "    \"src\": \"https://sunnynoodlebucket.oss-cn-shanghai.aliyuncs.com/avatar/" + fileName + ".jpg" + ".jpg\"" +
                "  }" +
                "}       ";
    }

    //课表
    @GetMapping("/curriculum")
    public String toCurriculumPage(Model model) {
        String uId = getUserId();
        List<StudentCourseVo> courseList = studentCourseVoService.list(new QueryWrapper<StudentCourseVo>().eq("teacher_id", uId).orderByAsc("day_time"));
        String courseJson = JSON.toJSONString(courseList);
        model.addAttribute("courses", courseJson);
        //System.out.println(courseJson);
        return "/newVersion/teacher/curriculum";
    }

    private String getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("username", userDetails.getUsername()));
        return u.getId();
    }

    private String getUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails.getUsername();
    }

    private String getTeacherName()
    {
        String userId= getUserId();
        if(userId==null)
            return null;
        TeacherInfo teacherInfo= teacherInfoService.getOne(new QueryWrapper<TeacherInfo>().eq("teacher_id",userId));
        if(teacherInfo==null)
            return null;
        String teacherName= teacherInfo.getTeacherName();
        if(StringUtils.isEmpty(teacherName))
        {
            return null;
        }
        else
        {
            return teacherName;
        }

    }

    @GetMapping("/pcc")
    public String getRequiredCourse(Model model) {
        return "/newVersion/teacher/professional_compulsory_course";
    }
    @GetMapping("/pcc.json")
    @ResponseBody
    public String getPccJson()
    {
        CourseVoDTO courseVoDTO=new CourseVoDTO();
        String uName = getTeacherName();
        if(StringUtils.isEmpty(uName))
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setMsg("未绑定姓名");
            return JSON.toJSONString(courseVoDTO);
        }
        List<CourseVo> courseVoList = courseVoService.list(new QueryWrapper<CourseVo>().select(CourseVo.class,
                info -> !info.getColumn().equals("course_type")
                        && !info.getColumn().equals("student_id"))
                .eq("teacher_name", uName).eq("course_type", "专业必修课"));
        if(courseVoList.isEmpty())
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setMsg("无数据");
            courseVoDTO.setCount(0);
        }
        else
        {
            courseVoDTO.setCode(0);
            courseVoDTO.setMsg("无数据");
            courseVoDTO.setCount(courseVoList.size());
        }
        courseVoDTO.setData(courseVoList);
        return JSON.toJSONString(courseVoDTO);
    }

    @GetMapping("/pe")
    public String getElectiveCourse(Model model) {
        return "/newVersion/teacher/professional_compulsory_course";
    }
    @GetMapping("/pe.json")
    @ResponseBody
    public String getPeJson()
    {
        CourseVoDTO courseVoDTO=new CourseVoDTO();
        String uName = getTeacherName();
        if(StringUtils.isEmpty(uName))
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setMsg("未绑定姓名");
            return JSON.toJSONString(courseVoDTO);
        }
        List<CourseVo> courseVoList = courseVoService.list(new QueryWrapper<CourseVo>().select(CourseVo.class,
                info -> !info.getColumn().equals("course_type")
                        && !info.getColumn().equals("student_id"))
                .eq("teacher_name", uName).eq("course_type", "专业选修课"));
        if(courseVoList.isEmpty())
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setMsg("无数据");
            courseVoDTO.setCount(0);
        }
        else
        {
            courseVoDTO.setCode(0);
            courseVoDTO.setMsg("无数据");
            courseVoDTO.setCount(courseVoList.size());
        }
        courseVoDTO.setData(courseVoList);
        return JSON.toJSONString(courseVoDTO);
    }

    @GetMapping("/gc")
    public String getGeneralCourse(Model model) {
        return "/newVersion/teacher/general_course";

    }
    @GetMapping("/gc.json")
    @ResponseBody
    public String getGcJson()
    {
        CourseVoDTO courseVoDTO=new CourseVoDTO();
        String uName = getTeacherName();
        if(StringUtils.isEmpty(uName))
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setMsg("未绑定姓名");
            return JSON.toJSONString(courseVoDTO);
        }
        List<CourseVo> courseVoList = courseVoService.list(new QueryWrapper<CourseVo>().select(CourseVo.class,
                info -> !info.getColumn().equals("course_type")
                        && !info.getColumn().equals("student_id"))
                .eq("teacher_name", uName).eq("course_type", "通识课"));
        if(courseVoList.isEmpty())
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setMsg("无数据");
            courseVoDTO.setCount(0);
        }
        else
        {
            courseVoDTO.setCode(0);
            courseVoDTO.setMsg("无数据");
            courseVoDTO.setCount(courseVoList.size());
        }
        courseVoDTO.setData(courseVoList);
        return JSON.toJSONString(courseVoDTO);
    }

    @GetMapping("/detail/{courseDetailId}")
    public  String getDetail(Model model, @PathVariable("courseDetailId") String courseDetailId)
    {
        CourseDetail courseDetail = courseDetailService.getOne(new QueryWrapper<CourseDetail>().eq("course_detail_id", courseDetailId));
        CourseDetailDTO courseDetailDTO = new CourseDetailDTO();
        BeanUtils.copyProperties(courseDetail,courseDetailDTO);
        CourseInfo courseInfo = courseInfoService.getOne(new QueryWrapper<CourseInfo>().eq("course_id", courseDetail.getCourseId()));
        courseDetailDTO.setCredit(courseInfo.getCredit());
        courseDetailDTO.setCreditHours(courseInfo.getCreditHours());
        model.addAttribute("courseDetail",courseDetailDTO);
        return "/newVerison/teacher/courseDetail";
    }
}