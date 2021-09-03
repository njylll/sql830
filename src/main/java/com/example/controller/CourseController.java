package com.example.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.DemoApplication;
import com.example.dto.CourseDTO;
import com.example.dto.CourseDetailDTO;
import com.example.entity.*;
import com.example.service.*;
import com.example.service.impl.StudentInfoServiceImpl;
import com.example.service.impl.StudentVoServiceImpl;
import com.example.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {
    public static final Logger log = LoggerFactory.getLogger(DemoApplication.class);


    @Autowired
    private CourseDetailDTOService courseDetailDTOService;

    @Autowired
    private CourseInfoService courseInfoService;

    @Autowired
    private CourseDetailService courseDetailService;

    @Autowired
    private CourseTimeService courseTimeService;


    /**
     * 查询课程信息
     * @param model
     * @return
     */
    @GetMapping("/course/courseInfoList")
    public String getCourseInfoList(Model model)
    {
        List<CourseInfo> courseInfoList = courseInfoService.listAll();

        model.addAttribute("courseInfoList", courseInfoList);

        return "/course/courseInfoList";
    }

    /**
     * 查询课程detail
     * @param model
     * @return
     */
    @GetMapping("/course/courseDetailList/{courseId}")
    public String getCourseDetailList(@PathVariable("courseId")String courseId, Model model)
    {
        List<CourseDetailDTO> courseDetailDTOList = courseDetailDTOService.queryByCourseId(courseId);
        model.addAttribute("courseDetailList",courseDetailDTOList);

        return  "/course/courseDetailList";
    }

    /**
     *查询课程
     */
    @PostMapping("/course/search")
    public String searchCourse(CourseInfo courseInfo,
                               Model model)
    {
        QueryWrapper<CourseInfo> courseInfoQueryWrapper=new QueryWrapper<>();
        Map<String,String> eqMap=new HashMap<>();

        if(!StringUtils.isEmpty(courseInfo.getCourseId()))
            eqMap.put("course_id",courseInfo.getCourseId());

        if(!StringUtils.isEmpty(courseInfo.getCourseName()))
            eqMap.put("course_name",courseInfo.getCourseName());

        if(!StringUtils.isEmpty(courseInfo.getCourseType())){

                eqMap.put("course_type",courseInfo.getCourseType());
        }

        if(!StringUtils.isEmpty(courseInfo.getAssessmentMethod()))
            eqMap.put("assessment_method",courseInfo.getAssessmentMethod());

        if(!StringUtils.isEmpty(courseInfo.getResponsibleCollegeId()))
        {
            eqMap.put("responsible_college_id",courseInfo.getResponsibleCollegeId());
        }

        if(!StringUtils.isEmpty(courseInfo.getTeachingWay()))
        {
            eqMap.put("teaching_way",courseInfo.getTeachingWay());
        }

        if(!StringUtils.isEmpty(String.valueOf(courseInfo.getCreditHours())))
            eqMap.put("credit_hours",courseInfo.getCreditHours().toString());


        if(!StringUtils.isEmpty(String.valueOf(courseInfo.getCredit())))
            eqMap.put("credit",courseInfo.getCredit().toString());



        courseInfoQueryWrapper.allEq(eqMap);
        List<CourseInfo> courseInfoList = courseInfoService.list(courseInfoQueryWrapper);
        model.addAttribute("courseInfoList",courseInfoList);

        return "/course/courseInfoList";
    }

    @GetMapping("/course/addCourseInfo")
    public String toAddInfoPage()
    {
        return "/course/addCourseInfo";
    }

    /**
     * 添加课程
     * @param courseInfo
     * @return
     */
    @PostMapping("/course/doAddCourseInfo")
    public String addCourseInfo(CourseInfo courseInfo) {
        courseInfoService.add(courseInfo);
        return "redirect:/course/courseInfoList";
    }


    @GetMapping("/course/addCourseDetail")
    public String toAddDetailPage()
    {
        return "/course/addCourseDetail";
    }

    /**
     * 添加课程详情
     * @return
     */
    @PostMapping("/course/doAddCourseDetail")
    public String addCourseDetail(CourseDetailDTO courseDetailDTO) {
        courseDetailDTOService.add(courseDetailDTO);

       String courseId = courseDetailDTO.getCourseId();
        return "redirect:/course/courseDetailList"+courseId;
    }


    /**
     *修改课程信息
     * @return
     */
    @GetMapping("/course/updateInfo/{courseId}")
    public String toEditInfoPage(@PathVariable("courseId") String courseId
                            ,Model model)
    {
        //查询要修改的id
        CourseInfo courseInfo = courseInfoService.getOne(new QueryWrapper<CourseInfo>().eq("course_id",courseId));
        model.addAttribute("courseInfo",courseInfo);

        return "course/changeInfo";
    }


    @PostMapping(value = {"/course/doUpdateInfo"})
    public String doEditInfo(CourseInfo courseInfo) {
        String courseId=courseInfo.getCourseId();
        courseInfoService.update(courseInfo,new QueryWrapper<CourseInfo>().eq("course_id",courseId));

        return "redirect:/course/courseInfoList";

    }

    /**
     *修改课程详情
     * @return
     */
    @GetMapping("/course/updateDetail/{courseDetailId}")
    public String toEditDetailPage(@PathVariable("courseDetailId") String courseDetailId
            ,Model model)
    {
        CourseDetailDTO courseDetailDTO = courseDetailDTOService.queryByCourseDetailId(courseDetailId);
        model.addAttribute("courseDetail",courseDetailDTO);
        return "course/changeDetail";
    }


    @PostMapping(value = {"/course/doUpdateDetail"})
    public String doEditDetail(CourseDetailDTO courseDetailDTO) {
        String courseDetailId = courseDetailDTO.getCourseDetailId();
        courseDetailDTOService.update(courseDetailDTO);

        return "redirect:/course/courseInfoList"+courseDetailId;

    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("/course/{courseId}")
    public String deleteCourseInfo(@PathVariable("courseId") String courseId)
    {
        courseInfoService.remove(new QueryWrapper<CourseInfo>().eq("course_id",courseId));

        return "redirect:/course/courseInfoList";

    }

    /**
     * 删除课程详情
     */
    @DeleteMapping("/course/{courseDetailId}")
    public String deleteCourseDetail(@PathVariable("courseDetailId") String courseDetailId)
    {
        courseDetailDTOService.remove(courseDetailId);
        return "redirect:/course/courseInfoList";

    }




}
