package com.project.Booktion.controller;

import com.project.Booktion.model.Review;
import com.project.Booktion.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/main/review")
@RequiredArgsConstructor
public class reviewController {
    private final ReviewService reviewService;

    private List<Review> reviewList = new ArrayList<>();
    @GetMapping("/review/write") // 리뷰 폼 쓰기
    public String reviewForm(@ModelAttribute Review review){
        reviewList.add(review);
        return "redirect:/review";
    }

    @PostMapping("/review") // 리뷰 폼 제출
    public String reviewWrite(@ModelAttribute Review review) {

        if(review == null) {
            return "reviewForm";
        }
        reviewService.write(review);
        return "review";
    }

        @GetMapping("/reviewList")
        public String showReviewList(Model model) {
            model.addAttribute("reviewList", reviewList);
            return "reviewList";
        }
}
