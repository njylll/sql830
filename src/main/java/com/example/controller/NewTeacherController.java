package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newVersion/teacher")
public class NewTeacherController {
    @GetMapping(value = {"/","/index"})
    public String toSuperTeacherMainPage()
    {
        return "/newVersion/teacher/index";
    }

}
