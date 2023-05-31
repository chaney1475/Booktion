package com.project.Booktion.controller.user;

import com.project.Booktion.model.User;
import com.project.Booktion.repository.UserRepository;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/signUp")
@RequiredArgsConstructor
public class SignFormController { // 회원가입
    private final UserRepository userRepository;
    @GetMapping
    public String showSignUpForm(@ModelAttribute User user) {
        return "user/signUp";
    }

    @PostMapping
    public String processSignupForm(@ModelAttribute("user") User user, Model model) {
        User save = userRepository.save(user);
        model.addAttribute("name", save.getName());
        return "user/signupSuccess";
    }
}
