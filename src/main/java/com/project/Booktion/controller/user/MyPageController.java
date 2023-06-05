package com.project.Booktion.controller.user;

import com.project.Booktion.model.TempOrder;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.TempOrderRepository;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {
    private final TempOrderRepository tempOrderRepository;
    private final UserService userService;
    @GetMapping
    public String showMyPage(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {
            log.info("MyPageController#showMyPage is fail : user is null!!!!!!!!!!!!!");
            return "/user/signIn";
        }
        List<TempOrder> TempOrderList = tempOrderRepository.findByUserId(userId);
        User user = userService.getUser(userId);
        model.addAttribute("TempOrderList", TempOrderList);
        model.addAttribute("userInfo", user);
        return "myPage/main";
    }

    @PostMapping
    public String modifyUserInfo(HttpSession session, @ModelAttribute("userInfo")User user){
        log.info("user Info is change");
        user = userService.updateUser(user);
        return "myPage/main";
    }
}
