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
@RequestMapping("/used/registFrom")
@RequiredArgsConstructor
public class RegistUsedFormController {
    private final UsedBookService usedBookService;

    @GetMapping
    public String newForm(@RequestParam int sellerId,
                          @ModelAttribute("usedBookRegist") UsedBookRegist usedBookRegist){
        //중고책 등록 폼 생성
        usedBookRegist.setSellerId(sellerId);
        return "used/registForm";
    }

    @PostMapping
    public String secondForm(@ModelAttribute("usedBookRegist") UsedBookRegist UsedBookRegist){
        //등록할 책 지정완료
        return "used/registForm";//등록한 책 상세페이지로 이동
    }

    @RequestMapping("/submit")
    public String addForm(@ModelAttribute("usedBookRegist") UsedBookRegist UsedBookRegist,
                          BindingResult bindingResult, Model model){
        //중고책 등록 폼 제출
        if (bindingResult.hasErrors()) {
            return "redirect:used/registForm";//다시 폼으로 이동
        }
        UsedBook book = usedBookService.submitRegistForm(UsedBookRegist);
        model.addAttribute("UsedBookRegist", UsedBookRegist); //이거 필요한지 좀 고민 해봐야할듯
        return "used/bookInfo";//등록한 책 상세페이지로 이동
    }
}
