package com.project.Booktion.controller.user;

import com.project.Booktion.model.TempOrder;
import com.project.Booktion.repository.TempOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {
    private final TempOrderRepository tempOrderRepository;
    @GetMapping
    public String showMyPage(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        List<TempOrder> TempOrderList = tempOrderRepository.findByUserId(userId);
        model.addAttribute("TempOrderList", TempOrderList);
        return "myPage/main";
    }
}
