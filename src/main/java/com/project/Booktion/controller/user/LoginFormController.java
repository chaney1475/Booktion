package com.project.Booktion.controller.user;


import com.project.Booktion.model.User;
import com.project.Booktion.repository.UserRepository;
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
    private final UserRepository userRepository;
    @GetMapping
    public String signIn() {
        return "user/signIn";
    }

    @PostMapping
    public String processLogin(@RequestParam String userId, @RequestParam String password, Model model, HttpSession session) {
        System.out.println(userId);
        System.out.println(password);
        if (userRepository.findByUserIdAndPassword(userId, password) != null) {
            session.setAttribute("userId",userId);
            model.addAttribute("success","로그인에 성공했습니다");
            return "redirect:/main"; // 로그인 성공 시 리다이렉트할 URL
        } else {
            model.addAttribute("error", "로그인에 실패하였습니다\n다시 시도해 주세요");
            return "user/signIn"; // 로그인 폼으로 돌아가기
        }
    }

}
