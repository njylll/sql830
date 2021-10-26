package com.example.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.DemoApplication;
import com.example.dto.CourseDetailDTO;
import com.example.entity.*;
import com.example.service.*;
import com.example.service.impl.CollegeInfoServiceImpl;
import com.example.util.UUIDGenerator;
import com.example.vo.CourseInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private CollegeInfoServiceImpl collegeInfoService;


    /**
     * 查询课程信息
     * @param model
     * @return
     */
    @GetMapping("/course/courseInfoList")
    public String getCourseInfoList(Model model)
    {
        List<CourseInfo> courseInfoList = courseInfoService.listAll();
        List<CourseInfoVO> courseInfoVOList=new ArrayList<>();
        for (CourseInfo courseInfo : courseInfoList) {
            CourseInfoVO courseInfoVO=new CourseInfoVO();
            BeanUtils.copyProperties(courseInfo,courseInfoVO);

            QueryWrapper<CollegeInfo> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("college_id",courseInfo.getResponsibleCollegeId());
            courseInfoVO.setResponsibleCollegeName(collegeInfoService.getOne(queryWrapper).getCollegeName());
            courseInfoVOList.add(courseInfoVO);
        }
        List<String> idList= courseInfoService.listAllCourseId();
        model.addAttribute("cId",idList);

        List<String> courseNameList = courseInfoService.searchAllCourseName();
        model.addAttribute("cName",courseNameList);

        model.addAttribute("courseInfoList", courseInfoVOList);
        List<CourseInfo> creditHoursList = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct credit_hours").orderByAsc("credit_hours"));
        model.addAttribute("creditHours",creditHoursList);

        List<CollegeInfo> collegeInfos = collegeInfoService.list(new QueryWrapper<CollegeInfo>().select("distinct college_name"));
        model.addAttribute("collegeInfos",collegeInfos);

        List<CourseInfo> credit = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct credit").orderByAsc("credit"));
        model.addAttribute("creditList",credit);
        List<CourseInfo> teachingWayList = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct teaching_way"));
        model.addAttribute("teachingWayList",teachingWayList);
        List<CourseInfo> assessmentMethodList = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct assessment_method"));
        model.addAttribute("assessmentMethodList",assessmentMethodList);
        List<CourseInfo> courseTypeList = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct course_type"));
        model.addAttribute("courseTypeList",courseTypeList);
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
        if(courseId==null || courseId.equals("") || courseId.equals("null"))
        {
            return "redirect:/course/courseDetailList";
        }
        List<CourseDetailDTO> courseDetailDTOList = courseDetailDTOService.queryByCourseId(courseId);
        model.addAttribute("courseDetailList",courseDetailDTOList);
        return  "/course/courseDetailList";
    }

    /**
     * 所有课程详情
     * @param model
     * @return
     */
    @GetMapping("/course/courseDetailList")
    public String getAllCourseDetailList(Model model)
    {
        List<CourseDetailDTO> courseDetailDTOList = courseDetailDTOService.listAll();
        model.addAttribute("courseDetailList",courseDetailDTOList);
        return  "/course/courseDetailList";
    }

    /**
     *查询课程
     */
    @PostMapping("/course/search")
    public String searchCourse(CourseInfoVO courseInfo,
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

        if(!StringUtils.isEmpty(courseInfo.getResponsibleCollegeName()))
        {
            QueryWrapper<CollegeInfo> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("college_name",courseInfo.getResponsibleCollegeName());
            eqMap.put("responsible_college_id",collegeInfoService.getOne(queryWrapper).getCollegeId());
        }

        if(!StringUtils.isEmpty(courseInfo.getTeachingWay()))
        {
            eqMap.put("teaching_way",courseInfo.getTeachingWay());
        }

        if(courseInfo.getCreditHours()!=null)
            eqMap.put("credit_hours",courseInfo.getCreditHours().toString());


        if(courseInfo.getCredit()!=null)
            eqMap.put("credit",courseInfo.getCredit().toString());

        courseInfoQueryWrapper.allEq(eqMap);
        List<CourseInfo> courseInfoList = courseInfoService.list(courseInfoQueryWrapper);
        List<CourseInfoVO> courseInfoVOList=new ArrayList<>();
        for (CourseInfo info : courseInfoList) {
            CourseInfoVO courseInfoVO=new CourseInfoVO();
            BeanUtils.copyProperties(info,courseInfoVO);

            QueryWrapper<CollegeInfo> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("college_id",info.getResponsibleCollegeId());
            courseInfoVO.setResponsibleCollegeName(collegeInfoService.getOne(queryWrapper).getCollegeName());
            courseInfoVOList.add(courseInfoVO);
        }
        List<String> courseNameList = courseInfoService.searchAllCourseName();
        model.addAttribute("cName",courseNameList);
        List<String> idList= courseInfoService.listAllCourseId();
        model.addAttribute("cId",idList);
        List<CourseInfo> creditHours = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct credit_hours").orderByAsc("credit_hours"));
        model.addAttribute("creditHours",creditHours);
        model.addAttribute("courseInfoList",courseInfoVOList);
        List<CollegeInfo> collegeInfos = collegeInfoService.list(new QueryWrapper<CollegeInfo>().select("distinct college_name"));
        model.addAttribute("collegeInfos",collegeInfos);
        List<CourseInfo> credit = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct credit").orderByAsc("credit"));
        model.addAttribute("creditList",credit);
        List<CourseInfo> teachingWayList = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct teaching_way"));
        model.addAttribute("teachingWayList",teachingWayList);
        List<CourseInfo> assessmentMethodList = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct assessment_method"));
        model.addAttribute("assessmentMethodLst",assessmentMethodList);
        List<CourseInfo> courseTypeList = courseInfoService.list(new QueryWrapper<CourseInfo>().select("distinct course_type"));
        model.addAttribute("courseTypeList",courseTypeList);


        return "/course/courseInfoList";
    }

    /**
     * 添加课程页面
     * @return
     */
    @GetMapping("/course/addCourseInfo")
    public String toAddInfoPage()
    {
        return "/course/addCourseInfo";
    }

    /**
     * 添加课程
     * @return
     */
    @PostMapping("/course/doAddCourseInfo")
    public String addCourseInfo(CourseInfoVO vo) {
        String uuid= UUIDGenerator.generateCourseUUID(vo.getCourseId());
        vo.setUuid(uuid);
        QueryWrapper<CollegeInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("college_name",vo.getResponsibleCollegeName());
        CollegeInfo collegeInfo= collegeInfoService.getOne(queryWrapper);
        CourseInfo courseInfo=new CourseInfo();
        BeanUtils.copyProperties(vo,courseInfo);
        courseInfo.setCourseId(collegeInfo.getCollegeId());

        courseInfoService.add(courseInfo);
        return "redirect:/course/courseInfoList";
    }

    /**
     * 添加课程详情页面
     * @return
     */
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
        String uuid=UUIDGenerator.generateCourseDetailUUID(courseDetailDTO.getCourseId());
        courseDetailDTO.setCourseDetailId(uuid);
        courseDetailDTOService.add(courseDetailDTO);

       String courseId = courseDetailDTO.getCourseId();
        return "redirect:/course/courseDetailList";
    }


    /**
     *修改课程页面
     * @return
     */
    @GetMapping("/course/updateInfo/{courseId}")
    public String toEditInfoPage(@PathVariable("courseId") String courseId
                            ,Model model)
    {
        //查询要修改的id
        CourseInfo courseInfo = courseInfoService.getOne(new QueryWrapper<CourseInfo>().eq("course_id",courseId));
        QueryWrapper<CollegeInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("college_id",courseId);
        CollegeInfo collegeInfo= collegeInfoService.getOne(queryWrapper);
        CourseInfoVO courseInfoVO=new CourseInfoVO();
        BeanUtils.copyProperties(courseInfo,courseInfoVO);
        courseInfoVO.setResponsibleCollegeName(collegeInfo.getCollegeName());

        model.addAttribute("courseInfo",courseInfoVO);

        return "course/changeInfo";
    }

    /**
     * 修改课程
     * @param courseInfo
     * @return
     */
    @PostMapping(value = {"/course/doUpdateInfo"})
    public String doEditInfo(CourseInfo courseInfo) {
        String courseId=courseInfo.getCourseId();
        courseInfoService.update(courseInfo,new QueryWrapper<CourseInfo>().eq("course_id",courseId));

        return "redirect:/course/updateInfo/"+courseId;

    }

    /**
     *修改课程详情页面
     * @return
     */
    @GetMapping("/course/updateDetail/{courseDetailId}")
    public String toEditDetailPage(@PathVariable("courseDetailId") String courseDetailId
            , Model model)
    {
        CourseDetailDTO courseDetailDTO = courseDetailDTOService.queryByCourseDetailId(courseDetailId);
        model.addAttribute("courseDetail",courseDetailDTO);
        return "course/changeDetail";
    }

    /**
     * 修改课程详情
     * @param courseDetailDTO
     * @return
     */
    @PostMapping(value = {"/course/doUpdateDetail"})
    public String doEditDetail(CourseDetailDTO courseDetailDTO) {
        String courseId = courseDetailDTO.getCourseId();

        courseDetailDTOService.update(courseDetailDTO);

        return "redirect:/course/courseDetailList/"+courseId;

    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("/course/deleteInfo/{courseId}")
    public String deleteCourseInfo(@PathVariable("courseId") String courseId)
    {
        courseInfoService.remove(new QueryWrapper<CourseInfo>().eq("course_id",courseId));

        return "redirect:/course/courseInfoList";

    }

    /**
     * 删除课程详情
     */
    @DeleteMapping("/course/deleteDetail/{courseId}/{courseDetailId}")
    public String deleteCourseDetail(@PathVariable("courseDetailId") String courseDetailId,@PathVariable("courseId") String courseId)
    {
        courseDetailDTOService.remove(courseDetailId);
        return "redirect:/course/courseDetailList"+courseId;

    }




}
