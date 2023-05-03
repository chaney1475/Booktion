package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.UsedBook;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/used/regist")
@RequiredArgsConstructor
public class RegistUsedFormController {
    private final UsedBookService usedBookService;

    @GetMapping
    public String newForm(@RequestParam int sellerId,
                          @ModelAttribute("usedBookRegist") UsedBookRegist UsedBookRegist){
        //중고책 등록 폼 생성
        UsedBookRegist.setSellerId(sellerId);
        return "usedRegistForm";
    }

    @PostMapping
    public String addForm(@ModelAttribute("usedBookRegist") UsedBookRegist UsedBookRegist,
                          BindingResult bindingResult, Model model){
        //중고책 등록 폼 제출
        if (bindingResult.hasErrors()) {
            return "usedRegistForm";//다시 폼으로 이동
        }
        UsedBook book = usedBookService.submitRegistForm(UsedBookRegist);
        model.addAttribute("UsedBookRegist", UsedBookRegist);
        return "usedBookInfo";//등록한 책 상세페이지로 이동
    }
}
