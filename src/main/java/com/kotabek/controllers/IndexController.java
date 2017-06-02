package com.kotabek.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kotabek on 6/1/17.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String home() {
        return "redirect:/files";
    }
}
