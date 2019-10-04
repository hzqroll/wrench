package com.roll.wrench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zongqiang.hao
 * created on 2019-04-01 18:05.
 */
@Controller
public class MainController {
    @RequestMapping("/main")
    public String main() {
        return "俗称";
    }

    @RequestMapping("/profile")
    public String displayProfile() {
        return "profile/profilePage";
    }

    @RequestMapping("/index")
    public String index() {
        return "layout/default";
    }

    @RequestMapping("/chat")
    public String chat() {
        return "chat";
    }
}
