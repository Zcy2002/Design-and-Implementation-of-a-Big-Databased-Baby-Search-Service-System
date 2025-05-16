package com.iflytek.ylao.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class InitController {

    /**
     * 登录跳转
     **/
    @GetMapping("/")
    public String login(HttpSession session) {
        session.invalidate();
        return "login";
    }

    /**
     * 注册
     */
    @GetMapping("/register")
    public String register(HttpSession session){
        session.invalidate();
        return "register";
    }

    /**
     * 首页跳转
     **/
    @GetMapping("/indexR")
    public String indexr() {
        return "index-root";
    }

    @GetMapping("/indexV")
    public String indexv() {
        return "index-volunteer";
    }

    @GetMapping("/indexU")
    public String indexu() {
        return "/front/index";
    }

    /**
     * 404跳转
     **/
    @GetMapping("/404")
    public String error() {
        return "page/404";
    }
}
