package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newVersion/student")
public class NewStudentController {

    @GetMapping(value = {"/","/index"})
    public String toStudentMainPage()
    {
        return "/newVersion/student/index";
    }

}
