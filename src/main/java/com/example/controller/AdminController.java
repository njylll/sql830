package com.example.controller;

import com.example.vo.AdminAccountVO;
import com.example.service.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * 控制层
 */
@Controller
@RequestMapping(value = "/admin")
class AdminController {


    @Autowired(required=false)
    private AdminAccountService adminAccountService;

    /*
     * 跳转到首页
     */
    @RequestMapping("/adminIndex")
    public String userIndex(HttpServletRequest request,Model model) throws Exception{
        List<AdminAccountVO> userList = adminAccountService.queryByAll();
        int total = userList.size();
        model.addAttribute("adminList", userList);
        model.addAttribute("total", total);
        return "admin/AdminIndex";
    }


    /*
     * 进入修改
     */
    @RequestMapping("/adminEdit/{userid}")
    public ModelAndView  userEdit(@PathVariable("userid") String id,Model model) throws Exception{
        ModelAndView mv = new ModelAndView();
        AdminAccountVO admin = adminAccountService.queryById(id);
        model.addAttribute("admin", admin);
        mv.setViewName("admin/AdminEdit");
        return mv;
    }

    /*
     * 修改
     */
    @RequestMapping("/adminEditSubmit")
    public String  adminEditSubmit(AdminAccountVO admin,Model model,HttpServletRequest request) throws Exception{
        try{
            adminAccountService.update(admin);
            request.setAttribute("msg", "修改成功！");
            return "admin/AdminEdit";
        }catch (Exception e){
            request.setAttribute("msg", "修改失败！");
            return "admin/AdminEdit";
        }
    }

    /*
     * 进入添加
     */
    @RequestMapping("/adminAdd")
    public String  adminAdd(Model model,HttpServletRequest request) throws Exception{
        return "admin/AdminAdd";
    }


    /*
     * 增加
     */
    @RequestMapping("/adminAddSub")
    public String  adminAddSub(AdminAccountVO admin,Model model,HttpServletRequest request) throws Exception{
        try{
            adminAccountService.add(admin);
            request.setAttribute("msg", "添加成功！");
            return "admin/AdminAdd";
        }catch (Exception e){
            request.setAttribute("msg", "添加失败！");
            return "admin/AdminAdd";
        }
    }



}