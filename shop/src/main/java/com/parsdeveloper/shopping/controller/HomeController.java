package com.parsdeveloper.shopping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping({"/hello"})
    public String homePage() {
        return "home page ";
    }
}
