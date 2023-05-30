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
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class LoginFormController {

    private UserService userService;
    @GetMapping
    public String signIn() {
        return "signIn";
    }

    @PostMapping
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.authenticateUser(username, password);
        if (user != null) {
            // 로그인 성공 시 모델 객체에 사용자 정보 저장
            String userId = user.getUserId();
            session.setAttribute("userId",userId);
            return "redirect:/main"; // 로그인 성공 시 리다이렉트할 URL
        } else {
            model.addAttribute("error", "로그인에 실패하였습니다 다시 시도해 주세요");
            return "signIn"; // 로그인 폼으로 돌아가기
        }
    }

}
