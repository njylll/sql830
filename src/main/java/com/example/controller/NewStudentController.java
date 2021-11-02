package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.StudentCourseMapper;
import com.example.dao.StudentCourseVoMapper;
import com.example.dao.StudentInfoMapper;
import com.example.dao.UserMapper;
import com.example.dto.CourseDetailDTO;
import com.example.dto.CourseDetailVoDTO;
import com.example.dto.CourseModuleDTO;
import com.example.dto.CourseVoDTO;
import com.example.entity.*;
import com.example.service.CourseDetailService;
import com.example.service.CourseInfoService;
import com.example.service.CourseVoService;
import com.example.service.impl.*;
import com.mysql.cj.xdevapi.JsonParser;
import net.bytebuddy.utility.RandomString;
import org.apache.tomcat.util.security.PrivilegedSetTccl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import java.util.ArrayList;
import java.util.List;

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
    private StudentCourseServiceImpl studentCourseService;
    @Autowired
    private StudentCourseVoServiceImpl studentCourseVoService;
    @Autowired
    private CourseDetailService courseDetailService;
    @Autowired
    private CourseDetailVoServiceImpl courseDetailVoService;

    @Autowired
    private CourseVoService courseVoService;

    @Autowired
    private  CourseInfoService courseInfoService;
    @Autowired
    private CourseInfoVoServiceImpl courseInfoVoService;
    @Autowired
    private StudentInfoServiceImpl studentInfoService;


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
        String id;
        if(u!=null)
            id=u.getId();
        else
            id="";
        if(!StringUtils.isEmpty(id))
        {
            String sName= studentInfoService.getOne(new QueryWrapper<StudentInfo>().eq("student_id",id)).getStudentName();
            model.addAttribute("sName", sName);
        }
        model.addAttribute("sId",id);
        return "/newVersion/student/me";
    }
    //个人设置
    @PostMapping("/me")
    @ResponseBody
    public String doEditMe(@RequestParam("username")String username,
                           @RequestParam("studentId")String studentId,
                           @RequestParam("studentName")String studentName,
                           @RequestParam("password")String password,
                           HttpServletResponse response,
                           HttpServletRequest request)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        String originName=u.getUsername();
        if(!StringUtils.isEmpty(username))
            u.setUsername(username);
        //相当于取消绑定
        u.setId(studentId);
        if(!StringUtils.isEmpty(password))
            u.setPassword(password);
        userService.update(u,new QueryWrapper<User>().eq("username",originName));
        //绑定学生
        if(!StringUtils.isEmpty(studentId))
        {
            StudentInfo studentInfo=new StudentInfo();
            studentInfo.setStudentId(studentId);
            studentInfo.setStudentName(studentName);
            studentInfo.setUuid(RandomString.make(16));
            studentInfoService.remove(new QueryWrapper<StudentInfo>().eq("student_id",studentId));
            studentInfoService.save(studentInfo);
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

    //我的选课
    @GetMapping("/pcc")
    public String getRequiredCourse(Model model)
    {
        return "/newVersion/student/professional_compulsory_course";
    }

    @GetMapping("/pe")
    public String getElectiveCourse(Model model)
    {
        return "/newVersion/student/professional_elective";
    }

    @GetMapping("/gc")
    public String getLiberalCourse(Model model)
    {
        return "/newVersion/student/general_course";
    }
    @GetMapping("/pcc.json")
    @ResponseBody
    public String getPccJson()
    {
        CourseVoDTO courseVoDTO=new CourseVoDTO();
        String uId = getUserId();
        if(StringUtils.isEmpty(uId))
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setCount(0);
            courseVoDTO.setMsg("你没有绑定学号");
            return JSON.toJSONString(courseVoDTO);
        }

        List<CourseVo> courseVoList = courseVoService.list(new QueryWrapper<CourseVo>().select(CourseVo.class,
                info -> !info.getColumn().equals("course_type") && !info.getColumn().equals("student_id"))
                .eq("student_id",uId).eq("course_type","专业必修课"));
        if(courseVoList.size()==0)
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setCount(0);
            courseVoDTO.setMsg("无数据");
        }
        else
        {
            courseVoDTO.setCode(0);
            courseVoDTO.setCount(courseVoList.size());
            courseVoDTO.setMsg("ok");
        }
        courseVoDTO.setData(courseVoList);
        return JSON.toJSONString(courseVoDTO);
    }
    @GetMapping("/pe.json")
    @ResponseBody
    public String getPeJson()
    {
        CourseVoDTO courseVoDTO=new CourseVoDTO();
        String uId = getUserId();
        if(StringUtils.isEmpty(uId))
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setCount(0);
            courseVoDTO.setMsg("你没有绑定学号");
            return JSON.toJSONString(courseVoDTO);
        }
        List<CourseVo> courseVoList = courseVoService.list(new QueryWrapper<CourseVo>().select(CourseVo.class,
                info -> !info.getColumn().equals("course_type") && !info.getColumn().equals("student_id"))
                .eq("student_id",uId).eq("course_type","专业选修课"));
        if(courseVoList.size()==0)
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setCount(0);
            courseVoDTO.setMsg("无数据");
        }
        else
        {
            courseVoDTO.setCode(0);
            courseVoDTO.setCount(courseVoList.size());
            courseVoDTO.setMsg("ok");
        }
        courseVoDTO.setData(courseVoList);
        return JSON.toJSONString(courseVoDTO);
    }
    @GetMapping("/gc.json")
    @ResponseBody
    public String getGcJson()
    {
        CourseVoDTO courseVoDTO=new CourseVoDTO();
        String uId = getUserId();
        if(StringUtils.isEmpty(uId))
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setCount(0);
            courseVoDTO.setMsg("你没有绑定学号");
            return JSON.toJSONString(courseVoDTO);
        }
        List<CourseVo> courseVoList = courseVoService.list(new QueryWrapper<CourseVo>().select(CourseVo.class,
                info -> !info.getColumn().equals("course_type") && !info.getColumn().equals("student_id"))
                .eq("student_id",uId).eq("course_type","通识课"));
        if(courseVoList.size()==0)
        {
            courseVoDTO.setCode(1);
            courseVoDTO.setCount(0);
            courseVoDTO.setMsg("无数据");
        }
        else
        {
            courseVoDTO.setCode(0);
            courseVoDTO.setCount(courseVoList.size());
            courseVoDTO.setMsg("ok");
        }
        courseVoDTO.setData(courseVoList);
        return JSON.toJSONString(courseVoDTO);
    }
    //退选ajax
    @PostMapping("/deleteStudentCourse")
    @ResponseBody
    public String deleteStudentCourse(@RequestParam("courseDetailId")String courseDetailId)
    {
        String studentId=getUserId();
        studentCourseService.remove(new QueryWrapper<StudentCourse>().eq("student_id",studentId)
                .eq("course_detail_id",courseDetailId));
        return "ok";
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
        return "/newVersion/student/courseDetail";
    }

    //获得课程JSON
    @GetMapping("/PCCcourseModule.json")
    @ResponseBody
    public String getPccCourseInfo()
    {
        String userId=getUserId();
        if(StringUtils.isEmpty(userId))
        {
            CourseDetailVoDTO courseDetailVoDTO=new CourseDetailVoDTO();
            courseDetailVoDTO.setCode(5);
            courseDetailVoDTO.setMsg("没绑定学号");
            return JSON.toJSONString(courseDetailVoDTO);
        }
        List<StudentCourse> studentCourses= studentCourseService.list(new QueryWrapper<StudentCourse>().eq("student_id",userId));
        List<String> details = new ArrayList<>();
        for (StudentCourse s : studentCourses) {
            details.add(s.getCourseDetailId());
        }
        QueryWrapper<CourseDetailVo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_type","专业必修课");
        if(!details.isEmpty())
                queryWrapper.notIn("course_detail_id",details);
        List<CourseDetailVo>  courseDetailVos= courseDetailVoService.list(queryWrapper);
        CourseDetailVoDTO courseDetailVoDTO=new CourseDetailVoDTO();
        courseDetailVoDTO.setCode(0);
        courseDetailVoDTO.setCount(courseDetailVos.size());
        courseDetailVoDTO.setMsg("成功");
        courseDetailVoDTO.setData(courseDetailVos);

        return JSON.toJSONString(courseDetailVoDTO);
    }
    @GetMapping("/PEcourseModule.json")
    @ResponseBody
    public String getPeCourseInfo()
    {
        String userId=getUserId();
        if(StringUtils.isEmpty(userId))
        {
            CourseDetailVoDTO courseDetailVoDTO=new CourseDetailVoDTO();
            courseDetailVoDTO.setCode(5);
            courseDetailVoDTO.setMsg("没绑定学号");
            return JSON.toJSONString(courseDetailVoDTO);
        }
        List<StudentCourse> studentCourses= studentCourseService.list(new QueryWrapper<StudentCourse>().eq("student_id",userId));
        List<String> details = new ArrayList<>();
        for (StudentCourse s : studentCourses) {
            details.add(s.getCourseDetailId());
        }
        QueryWrapper<CourseDetailVo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_type","专业选修课");
        if(!details.isEmpty())
            queryWrapper.notIn("course_detail_id",details);
        List<CourseDetailVo>  courseDetailVos= courseDetailVoService.list(queryWrapper);
        CourseDetailVoDTO courseDetailVoDTO=new CourseDetailVoDTO();
        courseDetailVoDTO.setCode(0);
        courseDetailVoDTO.setCount(courseDetailVos.size());
        courseDetailVoDTO.setMsg("成功");
        courseDetailVoDTO.setData(courseDetailVos);

        return JSON.toJSONString(courseDetailVoDTO);
    }
    @GetMapping("/GCcourseModule.json")
    @ResponseBody
    public String getGcCourseInfo()
    {
        String userId=getUserId();
        if(StringUtils.isEmpty(userId))
        {
            CourseDetailVoDTO courseDetailVoDTO=new CourseDetailVoDTO();
            courseDetailVoDTO.setCode(5);
            courseDetailVoDTO.setMsg("没绑定学号");
            return JSON.toJSONString(courseDetailVoDTO);
        }
        List<StudentCourse> studentCourses= studentCourseService.list(new QueryWrapper<StudentCourse>().eq("student_id",userId));
        List<String> details = new ArrayList<>();
        for (StudentCourse s : studentCourses) {
            details.add(s.getCourseDetailId());
        }
        QueryWrapper<CourseDetailVo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_type","通识课");
        if(!details.isEmpty())
            queryWrapper.notIn("course_detail_id",details);
        List<CourseDetailVo>  courseDetailVos= courseDetailVoService.list(queryWrapper);
        CourseDetailVoDTO courseDetailVoDTO=new CourseDetailVoDTO();
        courseDetailVoDTO.setCode(0);
        courseDetailVoDTO.setCount(courseDetailVos.size());
        courseDetailVoDTO.setMsg("成功");
        courseDetailVoDTO.setData(courseDetailVos);

        return JSON.toJSONString(courseDetailVoDTO);
    }

    //选PCC页面
    @GetMapping("/choosePCC")
    public String toChoosePcc(Model model)
    {
        return "/newVersion/student/choosePCC";
    }
    //选PE页面
    @GetMapping("/choosePE")
    public String toChoosePee(Model model)
    {
        return "/newVersion/student/choosePE";
    }
    //选GC页面
    @GetMapping("/chooseGC")
    public String toChooseGc(Model model)
    {
        return "/newVersion/student/chooseGC";
    }
    //选课
    @PostMapping("/chooseCourse")
    @ResponseBody
    public String doChoose(@RequestParam("courseDetailId[]")String[] courseDetailId,
                           @RequestParam("courseType")String courseType,
                           HttpServletResponse response)
    {
        String userId=getUserId();
        if(StringUtils.isEmpty(userId))
        {
            response.setStatus(403);
            return "{\"msg\":\"你没有绑定学号！\"}";
        }
        List<StudentCourse> studentCourses=new ArrayList<>();
        for (String s : courseDetailId) {
            StudentCourse sc=new StudentCourse();
            sc.setCourseDetailId(s);sc.setStudentId(userId);
            studentCourses.add(sc);
        }
        studentCourseService.saveBatch(studentCourses);
        return "ok";
    }


}
