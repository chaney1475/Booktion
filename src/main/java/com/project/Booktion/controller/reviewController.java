package com.project.Booktion.controller;

import com.project.Booktion.model.Review;
import com.project.Booktion.model.User;
import com.project.Booktion.service.ReviewService;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class reviewController {

    private final ReviewService reviewService;

    // 내 리뷰 목록을 조회하는 기능
    @GetMapping // 리뷰 불러오기
    public String getReviews(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        List<Review> reviewList = reviewService.getReviewByUser(currentUser);
        model.addAttribute("reviews", reviewList);
        return "myPage/review/reviewList";
    }

    @GetMapping("/create")
    public String reviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "reviewForm";
    }

    @PostMapping("/create")
    public String createReview(@ModelAttribute("review") Review review, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        review.setUser(currentUser);
        reviewService.createReview(review);
        return "redirect:/myPage/";
    }

    @PostMapping("/{reviewId}/delete")
    public String deleteReview(@PathVariable("reviewId") long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/myPage/";
    }

    @GetMapping("/{reviewId}/edit")
    public String editReviewForm(@PathVariable("reviewId") long reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        return "editReview";
    }

    @PostMapping("/{reviewId}/edit")
    public String editReview(@PathVariable("reviewId") long reviewId, @ModelAttribute("review") Review review) {
        review.setReviewId(reviewId);
        reviewService.updateReview(review);
        return "redirect:/myPage/";
    }


//    @PostMapping("/login")
//    public String login(@RequestParam("clientId") String clientId, @RequestParam("password") String password, HttpSession session){
//        User user = UserService.login(clientId, password);
//        if(user!=null) {
//            session.setAttribute("currentUser", user);
//            return "redirect:/review/";
//        }
//        else {
//            return "redirect:/review/";
//        }
//    }

//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.removeAttribute("currentUser");
//        return "redirect:/";
//    }

}
