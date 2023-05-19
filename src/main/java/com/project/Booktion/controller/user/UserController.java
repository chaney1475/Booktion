package com.project.Booktion.controller.user;

import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController { // 내 정보 보기
    private final UserService userService;

    @GetMapping("/{userId}")
    public String newForm(){
        return "login";
    }
    @PostMapping("/{userId}")
    public String addUser(){
        return "myPage/info/{userId}";
    }

    @PatchMapping("/{userId}")
    public String updateUser(){
        return "myPage";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(){
        return "main";
    }

}
