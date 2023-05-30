package com.project.Booktion.controller.user;


import com.project.Booktion.model.User;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@SessionAttributes("user")
public class LoginFormController {

    private UserService userService;
    @GetMapping
    public String showLoginForm() {
        return "login";
    }

    @PostMapping
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.authenticateUser(username, password);
        if (user != null) {
            // 로그인 성공 시 모델 객체에 사용자 정보 저장
            model.addAttribute("user", user);
            return "redirect:/main"; // 로그인 성공 시 리다이렉트할 URL
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // 로그인 폼으로 돌아가기
        }
    }

}
