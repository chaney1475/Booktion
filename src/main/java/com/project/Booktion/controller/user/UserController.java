package com.project.Booktion.controller.user;

import com.project.Booktion.model.User;
import com.project.Booktion.repository.UserRepository;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController { // 내 정보 보기

    private final UserRepository repository;
    @GetMapping("/{userId}")
    public String viewUserProfile(@PathVariable String userId, Model model) {
        User user = repository.findByUserId(userId);
        if (user == null) {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
            return "errorPage";
        }
        model.addAttribute("user", user);
        return "userProfile";
    }

    @PatchMapping("/{userId}")
    public String updateUserDetails(@PathVariable("userId") String userId, @ModelAttribute("user") User user) {
        // 사용자 정보 업데이트 로직
        User saved = repository.save(user);
        return "redirect:/user/" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        // 사용자 정보 삭제 로직
        repository.deleteById(userId);
        return "redirect:/main";
    }

}

