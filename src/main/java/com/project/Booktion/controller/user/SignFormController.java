package com.project.Booktion.controller.user;

import com.project.Booktion.model.User;
import com.project.Booktion.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/user")
public class SignFormController { // 회원가입
    private final UserService userService;

    public SignFormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signupForm";
    }

    @PostMapping("/signup")
    public String processSignupForm(@ModelAttribute("user") User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("successMessage", "회원가입이 완료되었습니다!");
        return "signupSuccess";
    }
}
