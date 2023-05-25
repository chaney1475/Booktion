package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.UsedBook;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/used/registFrom")
@RequiredArgsConstructor
@SessionAttributes("RegistUsedForm")
public class RegistUsedFormController {
    private final UsedBookService usedBookService;

    @ModelAttribute("usedBookRegist")
    public UsedBookRegist formData(){ //세션을 위한 객체 생성
        return new UsedBookRegist();
    }

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
        return "redirect:/used/registForm";//선택한 책 정보를 담아서 다시 폼으로 이동
    }

    @RequestMapping("/submit")
    public String addForm(@ModelAttribute("usedBookRegist") UsedBookRegist UsedBookRegist,
                          BindingResult bindingResult, Model model,
                          SessionStatus sessionStatus){
        //중고책 등록 폼 제출
        if (bindingResult.hasErrors()) {
            return "used/registForm";//다시 폼으로 이동
        }
        UsedBook book = usedBookService.submitRegistForm(UsedBookRegist);
        model.addAttribute("book", book); //이거 맞나?
        sessionStatus.setComplete();
        return "redirect:/used/bookInfo/" + book.getUsedBookId();//등록한 책 상세페이지로 이동
    }
}
