package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.DemoApplication;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    public static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String goRegister()
    {
        return "/register";
    }

    //响应首页
    @RequestMapping(value = "/bar")
    public String bar() {
        log.info("进入导航框架");
        return "/commons/bar";
    }

    @RequestMapping(value = { "/index"})
    public String welcome() {
        return "index";
    }

    //    用户登录拦截, "/index/login"加载有问题
//    拦截器无法渲染css，正则映射除index外的请求无法获取
//    @GetMapping(value = {"/{intercept:^(?!index).*$}",  "/{intercept:^(?!exit).*$}", "{intercept:^(?!/index/login).*$}"})
//    public String entry(HttpServletRequest request, @PathVariable String intercept) {
//
////    return "login";
//        Object user = request.getSession().getAttribute("loginUser");
//        if (user == null) {
//            //未登陆，返回登陆页面
//            log.info("进入登录拦截方法");
//            request.setAttribute("msg", "没有权限请先登陆");
////            获取到转发器，请求和响应由MyMVCconfig转发到index
//            // 服务器内部转发，可以带回request
//            return "redirect:/login";  //重定向到登录界面            return false;
//
//        } else {
//            System.out.println("\n已登陆，放行请求");
//        }
//        return "redirect:" + request.getContextPath();
//    }

    @GetMapping(value = {"/**/login"})
    public String login() {
        return "login";
    }

    //    @ModelAttribute(value = "username")String username,
//    @ModelAttribute(value = "password") String password,
//    Map<String, Object> map, HttpSession session
//    @RequestParam(value = "username", defaultValue = "1") String username,
//    @RequestParam(value = "password") String password
//
    @GetMapping(value = {"/log"})
    public String logCSS() {
        return "unusedLogin";
    }

    //Restcontroller的页面跳转后model参数传递未完成
    @PostMapping(value = {"/login"})
    public String login(@RequestParam(value = "username", defaultValue = "1") String username,
                        @RequestParam(value = "password") String password,
                        Map<String, Object> map,
                        HttpSession session)
    {
        Map<String,String> mmap = new HashMap<String,String>();
        mmap.put("username",username);
        mmap.put("password",password);
        User user = userService.getOne(new QueryWrapper<User>().allEq(mmap));
        if (user != null) {
//            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser", username);
//            return "redirect:/main.html";//重定向到登录内
            return "dashboard";
//        ModelAndView mv =new ModelAndView();
//        mv.setViewName("/login");
//        mv.addObject(String username);
        } else {
            //登录失败,msg传给模板引擎
            map.put("msg", "用户名或密码错误,请重新输入");
            return "login";
        }
    }
        /**
         * 用户注册
         */
        @GetMapping("/register")
        public String register(){
                return "register";
        }

        @PostMapping("/doRegister")
        public String doRegister(@RequestParam(value = "username", defaultValue = "1") String username,
                                 @RequestParam(value = "password") String password,Map<String,Object> map)
        {
            if(username.equals("")||password.equals("")){
                map.put("err1","账号和密码不能为空");
                return "register";
            }
            //判断username是否已存在
            User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
            if(user != null) {
                map.put("err2","用户名已存在");
                return "register";
            }
            else {
                User user1 = new User();
                user1.setUsername(username);
                user1.setPassword(password);
                userService.save(user1);
                return "login";
            }
        }


        @GetMapping("/exit")
    public String signOut(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
//            注销清空session
            session.removeAttribute("loginUser");
            log.info("去除session进入登出跳转方法");

            return "index";
        } else

        return "index";
    }
}


