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
    @GetMapping("/")
    public String getReviews(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        List<Review> reviews = reviewService.getReviewByUser(currentUser);
        model.addAttribute("reviews", reviews);
        return "review";
    }
    @GetMapping("/create") // 리뷰 폼을 보여주는 기능
    public String reviewForm(Model model){
        model.addAttribute("review", new Review());
        return "reviewForm";
    }

    @PostMapping("/create") // 리뷰 생성 기능
    public String createReview(@ModelAttribute("review") Review review, HttpSession session) {

        User currentUser = (User)session.getAttribute("currentUser");
        review.setUser(currentUser);
        reviewService.createReview(review);
        return "reviewSubmitted";
    }
    @PostMapping("/{reviewId}/delete") // 리뷰 삭제 기능
    public String deleteReview(@PathVariable("reviewId") long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/review/";
    }

    @GetMapping("/{reviewId}/edit") // 리뷰 수정 폼을 보여주는 기능
    public String editReviewForm(@PathVariable("reviewId") long reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        return "edit_review";
    }

    @PostMapping("/{reviewId}/edit") // 리뷰 수정하는 기능
    public String editReview(@PathVariable("reviewId") long reviewId, @ModelAttribute("review") Review review) {
        review.setReviewId(reviewId);
        reviewService.updateReview(review);
        return "redirect:/review/";
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("currentUser");
        return "redirect:/";
    }

}
