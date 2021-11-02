package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.CourseInfoMapper;
import com.example.dao.CourseVoMapper;
import com.example.dao.UserMapper;
import com.example.dto.CourseModuleDTO;
import com.example.dto.CourseTimeVoDTO;
import com.example.entity.*;
import com.example.service.CourseVoService;
import com.example.service.InviteCodeService;
import com.example.service.impl.*;
import com.example.util.InviteCode;
import javafx.util.converter.LocalDateStringConverter;
import net.bytebuddy.utility.RandomString;
import org.hashids.Hashids;
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
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;

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
    @Autowired
    private CourseDetailVoServiceImpl courseDetailVoService;
    @Autowired
    private CourseTimeServiceImpl courseTimeService;
    @Autowired
    private CourseTimeVoServiceImpl courseTimeVoService;
    @Autowired
    private CourseVoService courseVoService;
    @Autowired
    private CourseVoMapper courseVoMapper;


    @Autowired
    private InviteCodeService inviteCodeService;

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
    //ajax创建课程
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
        String rdStr=RandomString.make(16);
        courseDetail.setCourseDetailId(rdStr);
        courseDetail.setCourseCondition("正常");
        courseDetail.setTeacherName(teacherName);
        courseDetail.setStartSchoolYear(LocalDate.parse(start,DateTimeFormatter.ISO_DATE));
        courseDetail.setEndSchoolYear(LocalDate.parse(end,DateTimeFormatter.ISO_DATE));
        courseDetail.setStartTerm((byte) term);
        courseDetail.setTeachingLocation(location);

        courseDetailService.save(courseDetail);
        CourseTime courseTime=new CourseTime();
        courseTime.setCourseDetailId(rdStr);
        courseTime.setCourseTimeId(RandomString.make(16));
        //保存时间
        courseTimeService.save(courseTime);

        return "ok";
    }

    //课程详情界面
    @GetMapping("/editCourseDetail")
    public String toEditCourseDetailPage()
    {
        return "/newVersion/superTeacher/editCourseDetail";
    }
    //课程详情json
    @GetMapping("/courseDetail.json")
    @ResponseBody
    public String getCourseDetailJson()
    {
        List<CourseDetailVo> courseDetailVos= courseDetailVoService.list();
        CourseDetailDTO courseDetailDTO=new CourseDetailDTO();
        courseDetailDTO.setCode(0);
        courseDetailDTO.setCount(courseDetailVos.size());
        courseDetailDTO.setData(courseDetailVos);
        courseDetailDTO.setMsg("成功");
        return JSON.toJSONStringWithDateFormat(courseDetailDTO, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
    }
    //ajax删除课程详情
    @PostMapping("/deleteCourseDetail")
    @ResponseBody
    public String deleteCourseDetail(@RequestParam("courseDetailId")String courseDetailId)
    {
        courseDetailService.remove(new QueryWrapper<CourseDetail>().eq("course_detail_id",courseDetailId));
        return "ok";
    }

    //ajax修改课程
    @PostMapping("/editCourseDetail")
    @ResponseBody
    public String editCourseDetail(CourseDetailVo vo)
    {
        CourseDetail courseDetail=new CourseDetail();
        courseDetail.setCourseId(vo.getCourseId());
        courseDetail.setCourseDetailId(vo.getCourseDetailId());
        courseDetail.setStartSchoolYear(vo.getStartSchoolYear());
        courseDetail.setEndSchoolYear(vo.getEndSchoolYear());
        courseDetail.setStartTerm(vo.getStartTerm());
        courseDetail.setCourseCondition(vo.getCourseCondition());
        courseDetail.setTeacherName(vo.getTeacherName());
        courseDetail.setTeachingLocation(vo.getTeachingLocation());
        courseDetailService.update(courseDetail,new QueryWrapper<CourseDetail>().eq("course_detail_id",vo.getCourseDetailId()));
        return "ok";
    }

    //安排时间页面
    @GetMapping("/editCourseTime")
    public String toTimePage()
    {
        return "/newVersion/superTeacher/courseTime";
    }

    //课程时间JSON
    @GetMapping("/courseTime.json")
    @ResponseBody
    public String getCourseTimeJson()
    {
        List<CourseTimeVo> timeVos = courseTimeVoService.list();
        CourseTimeVoDTO courseTimeVoDTO=new CourseTimeVoDTO();
        if(timeVos.size()==0)
        {
            courseTimeVoDTO.setCode(1);
            courseTimeVoDTO.setCount(0);
            courseTimeVoDTO.setMsg("无数据");
        }
        else
        {
            courseTimeVoDTO.setCode(0);
            courseTimeVoDTO.setCount(timeVos.size());
            courseTimeVoDTO.setMsg("ok");
        }
        courseTimeVoDTO.setData(timeVos);
        return JSON.toJSONStringWithDateFormat(courseTimeVoDTO, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
    }

    //添加时间页面
    @GetMapping("/createCourseTime")
    public String toCreateTimePage(Model model)
    {
        List<CourseDetailVo> courseDetailVos =courseDetailVoService.list(new QueryWrapper<CourseDetailVo>().select("distinct course_name"));
        model.addAttribute("courseNames",courseDetailVos);
        return "/newVersion/superTeacher/createCourseTime";
    }
    //删除时间
    @PostMapping("/deleteCourseTime")
    @ResponseBody
    public String deleteTime(@RequestParam("courseTimeId") String courseTimeId)
    {
        courseTimeService.remove(new QueryWrapper<CourseTime>().eq("course_time_id",courseTimeId));
        return "ok";
    }
    @PostMapping("/editCourseTime")
    @ResponseBody
    public String doEditTime(CourseTimeVo vo)
    {
        CourseTime courseTime=new CourseTime();
        courseTime.setCourseTimeId(vo.getCourseTimeId());
        courseTime.setCourseDetailId(vo.getCourseDetailId());
        courseTime.setStartWeek(vo.getStartWeek());
        courseTime.setEndWeek(vo.getEndWeek());
        courseTime.setDayTime(vo.getDayTime());
        courseTime.setSectionStart(vo.getSectionStart());
        courseTime.setSectionEnd(vo.getSectionEnd());
        courseTimeService.update(courseTime,new QueryWrapper<CourseTime>().eq("course_time_id",vo.getCourseTimeId()));

        return "ok";
    }

    //添加课程时间
    @PostMapping("/createCourseTime")
    @ResponseBody
    public String doCreateTime(CourseTime courseTime)
    {
        courseTime.setCourseTimeId(RandomString.make(16));
        courseTimeService.save(courseTime);
        return "ok";
    }
    //搜索课程时间表里的课程详情id
    @PostMapping("/searchCourseDetailId")
    @ResponseBody
    public String searchDetailId(@RequestParam("courseName")String courseName)
    {
        List<CourseDetailVo> detailVos = courseDetailVoService.list(new QueryWrapper<CourseDetailVo>().eq("course_name",courseName));
        List<String> details=new ArrayList<>();
        for (CourseDetailVo detailVo : detailVos) {
            details.add(detailVo.getCourseDetailId());
        }
        return JSON.toJSONString(details);
    }


    @GetMapping("/generateCode")
    public String toCodePage()
    {
        return "newVersion/superTeacher/generateCode";
    }

    @PostMapping("/generateCode")
    @ResponseBody
    public String generateCode(@RequestParam("tNum") int tNum, @RequestParam("sNum") int sNum)
    {
        HashMap<String, ArrayList<String>> codeMap = new HashMap<>();
        ArrayList<String> tCode = new ArrayList<>();
        ArrayList<String> stCode = new ArrayList<>();
        while(tNum > 0) {
            String code = InviteCode.getInviteCode();
            inviteCodeService.save(code,"teacher");
            tCode.add(code);
            tNum--;
        }
        while(sNum > 0) {
            String code = InviteCode.getInviteCode();
            inviteCodeService.save(code,"super_teacher");
            stCode.add(code);
            sNum--;
        }

        codeMap.put("tCode",tCode);
        codeMap.put("stCode",stCode);
        return JSON.toJSONString(codeMap);
    }

    private String getUserId()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User u= userMapper.selectOne(new QueryWrapper<User>().eq("username",userDetails.getUsername()));
        return u.getId();
    }
    @PostMapping("/newVersion/superTeacher/pcc")
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



        return "newVersion/superTeacher/courseInfoList";
    }

}
