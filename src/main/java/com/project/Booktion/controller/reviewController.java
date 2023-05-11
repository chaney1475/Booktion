package com.project.Booktion.controller;

import com.project.Booktion.model.Review;
import com.project.Booktion.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main/review")
@RequiredArgsConstructor
public class reviewController {
    private final ReviewService reviewService;

    @GetMapping("/review/write") // 리뷰 폼 쓰기
    public String reviewForm(){
        return "reviewForm";
    }

    @PostMapping("/review/writeComplete") // 리뷰 폼 제출
    public String reviewWrite(Review review, Model model) {

        if(review == null) {
            return "reviewForm";
        }
        reviewService.write(review);
        model.addAttribute("message", "글작성이 완료되었습니다.");

        return "review";
    }

}
