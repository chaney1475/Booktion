package com.project.Booktion.controller;

import com.project.Booktion.model.*;
import com.project.Booktion.service.BookService;
import com.project.Booktion.service.OrderService;
import com.project.Booktion.service.ReviewService;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class reviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final BookService bookService;
    private final OrderService orderService;

    @GetMapping
    public String viewReviewList(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "user/signIn";
        }

        String userId = (String) session.getAttribute("userId");
        User user = userService.getUser(userId);
        if (user == null) {
            return "user/signIn";
        }

        List<Review> reviewList = reviewService.getReviewByUser(user);
        model.addAttribute("reviews", reviewList);

        return "myPage/review/reviewList";
    }

    @GetMapping("/editReview/{reviewId}") // Add this mapping for the edit review form
    public String showEditReviewForm(@PathVariable("reviewId") long reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        if (review == null) {
            // Handle the case where the review does not exist
            return "error-page";
        }
        model.addAttribute("review", review);
        return "mypage/review/editReview";
    }

    @PostMapping("/editReview/{reviewId}") // 리뷰 수정
    public String editReview(@PathVariable("reviewId") long reviewId, @ModelAttribute("review") Review editedReview) {
        Review existingReview = reviewService.getReviewById(reviewId);
        if (existingReview == null) {
            return "error-page";
        }

        existingReview.setTitle(editedReview.getTitle());
        existingReview.setContents(editedReview.getContents());
        reviewService.updateReview(existingReview);

        return "redirect:/review";
    }

    @GetMapping("/createReview") // 리뷰 작성
    public String showCreateReviewForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "user/signIn";
        }

        String userId = (String) session.getAttribute("userId");
        User user = userService.getUser(userId);
        if (user == null) {
            return "user/signIn";
        }

        List<Order> orderList = orderService.getOrdersByUser(user);
        if (orderList.isEmpty()) {
            // Handle the case where the user does not have any orders
            String errorMessage = "주문항목이 없습니다.";
            model.addAttribute("errorMessage", errorMessage);
            return "myPage/review/reviewList";
        }

        Order order = orderList.get(0); // Assuming you want to select the first order
        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems.isEmpty()) {
            // Handle the case where the order does not have any items
            String errorMessage = "주문항목이 없습니다.";
            model.addAttribute("errorMessage", errorMessage);
            return "myPage/review/reviewList";
        }

        // Assuming you want to select the first order item to write a review for
        OrderItem orderItem = orderItems.get(0);
        Book book = orderItem.getBook();
        if (book == null) {
            // Handle the case where the book does not exist
            String errorMessage = "책을 찾을 수 없습니다.";
            model.addAttribute("errorMessage", errorMessage);
            return "myPage/review/reviewList";
        }

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("review", new Review());
        return "mypage/review/reviewForm";
    }


    @PostMapping("/saveReview") // 작성한 리뷰 저장
    public String saveReview(@ModelAttribute("review") Review review, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "user/signIn";
        }

        String userId = (String) session.getAttribute("userId");
        User user = userService.getUser(userId);
        if (user == null) {
            return "user/signIn";
        }

        Long bookId = review.getBook().getBookId();
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            return "error-page";
        }

        review.setBook(book);

        review.setUserId(user);
        review.setCreateDate(LocalDateTime.now());
        reviewService.createReview(review);

        model.addAttribute("message", "리뷰가 작성되었습니다.");

        return "redirect:/review";
    }

    @GetMapping("/deleteReview/{reviewId}") // 리뷰 삭제
    public String deleteReview(@PathVariable("reviewId") long reviewId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "user/signIn";
        }

        String userId = (String) session.getAttribute("userId");
        User user = userService.getUser(userId);
        if (user == null) {
            return "user/signIn";
        }

        Review review = reviewService.getReviewById(reviewId);
        if (review == null || !review.getUserId().equals(user)) {
            // Handle the case where the review does not exist or the user does not own the review
            return "error-page";
        }

        reviewService.deleteReview(review);

        return "redirect:/review";
    }

}
