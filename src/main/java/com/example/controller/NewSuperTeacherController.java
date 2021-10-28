package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/newVersion/superTeacher")
public class NewSuperTeacherController {
    @GetMapping(value = {"/","/index"})
    public String toTeacherMainPage()
    {
        return "/newVersion/superTeacher/index";
    }

    @GetMapping("/me")
    public String toMe(HttpServletResponse response){
        return "/newVersion/superTeacher/me";
    }
}
