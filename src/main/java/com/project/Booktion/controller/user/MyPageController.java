package com.project.Booktion.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {
    @GetMapping
    public String showMyPage(HttpSession session,
                             Model model){
        return "myPage/main";
    }
}
