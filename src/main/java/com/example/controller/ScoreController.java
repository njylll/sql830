package com.example.controller;


import com.example.entity.CourseInfo;
import com.example.entity.StudentScore;
import com.example.service.AdminAccountService;
import com.example.service.CourseInfoService;
import com.example.service.StudentScoreService;
import com.example.vo.AdminAccountVO;
import com.example.vo.StudentScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
 *  控制层
 */
@Controller
@RequestMapping(value = "/score")
class ScoreController {


    @Autowired(required=false)
    private StudentScoreService scoreService;

    @Autowired(required=false)
    private CourseInfoService courseService;

    @Autowired(required=false)
    private AdminAccountService adminaccountService;


    /*
     * 跳转到首页
     */
    @RequestMapping("/score/list")
    public String scoreIndex(Model model) {
        List<StudentScore> scoreList = scoreService.queryByAll();
        model.addAttribute("scoreList", scoreList);
        return "score/list";
    }


    /*
     * 学生跳转到首页
     */
    @RequestMapping("/scoreIndex1")
    public String scoreIndex1(HttpServletRequest request,Model model) throws Exception{
        HttpSession session = request.getSession();
        String type = session.getAttribute("type").toString();
        String username = session.getAttribute("username").toString();
        List<StudentScoreVO> scoreList = scoreService.queryByAllAndStudent(username);
        int total = scoreList.size();
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("total", total);
        model.addAttribute("type", type);
        return "score/ScoreIndex";
    }

    /*
     * 进入修改
     */
    @RequestMapping("/scoreEdit/{id}")
    public ModelAndView  scoreEdit(@PathVariable("id") String id,Model model) throws Exception{
        ModelAndView mv = new ModelAndView();
        StudentScoreVO score = scoreService.queryById(id);
        List<CourseInfo> courseList = courseService.listAll();
        List<AdminAccountVO> userList = adminaccountService.queryByAll();
        model.addAttribute("score", score);
        model.addAttribute("courseList", courseList);
        model.addAttribute("userList", userList);
        mv.setViewName("score/ScoreEdit");
        return mv;
    }

    /*
     * 修改
     */
    @RequestMapping("/scoreEditSubmit")
    public String  scoreEditSubmit(StudentScoreVO score,Model model,HttpServletRequest request) throws Exception{
        try{
            AdminAccountVO user = adminaccountService.queryById(score.getStudentId());
            CourseInfo course = courseService.queryByCourseId(score.getcourseId());
            score.setStudentId(user.getuserid());
            score.setcourseId(course.getCourseId());
            scoreService.update(score);
            request.setAttribute("msg", "修改成功！");
            return "score/ScoreEdit";
        }catch (Exception e){
            request.setAttribute("msg", "修改失败！");
            return "score/ScoreEdit";
        }
    }

    /*
     * 进入添加
     */
    @RequestMapping("/scoreAdd")
    public String  scoreAdd(Model model,HttpServletRequest request) throws Exception{
        List<CourseInfo> courseList = courseService.listAll();
        List<AdminAccountVO> userList = adminaccountService.queryByAll();
        model.addAttribute("courseList", courseList);
        model.addAttribute("userList", userList);
        return "score/ScoreAdd";
    }

    /*
     * 增加
     */
    @RequestMapping("/scoreAddSub")
    public String  scoreAddSub(StudentScoreVO score,Model model,HttpServletRequest request) throws Exception{
        try{
            AdminAccountVO user = adminaccountService.queryById(score.getStudentId());
            CourseInfo course = courseService.queryByCourseId(score.getcourseId());
            score.setStudentId(user.getuserid());
            score.setcourseId(course.getCourseId());
            scoreService.add(score);
            request.setAttribute("msg", "添加成功！");
            return "score/ScoreAdd";
        }catch (Exception e){
            request.setAttribute("msg", "添加失败！");
            return "score/ScoreAdd";
        }
    }



    /*
     * 查询不及格学生
     */
    @RequestMapping("/scoreMin")
    public String scoreMin(HttpServletRequest request,Model model) throws Exception{
        HttpSession session = request.getSession();
        String type = session.getAttribute("type").toString();
        String username = session.getAttribute("username").toString();
        List<StudentScoreVO> scoreList = scoreService.queryByAll2();
        int total = scoreList.size();
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("total", total);
        model.addAttribute("type", type);
        return "static/index2";
    }

}