package com.project.Booktion.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/signOut")
public class LogoutController {

    @GetMapping
    public String logout(HttpSession session) {
        // 세션을 만료시킴으로써 로그아웃 처리
        session.invalidate();
        return "redirect:/main"; // 로그아웃 후 메인페이지로,
    }
}
