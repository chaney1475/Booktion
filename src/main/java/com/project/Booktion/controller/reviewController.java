package com.project.Booktion.controller;

import com.fasterxml.classmate.ResolvedTypeWithMembers;
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
        return "myPage/review/editReview";
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

    @GetMapping("/add") // 리뷰 작성
    public String reviewForm(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "user/signIn";
        }
        User user = userService.getUser(userId);

        List<Order> orderList = orderService.getOrdersByUser(user);
        List<Review> reviewList = reviewService.getReviewByUser(user);

        // 리뷰가 작성된 book
        List<Book> reviewedBook =new ArrayList<>();
        for(Review review:reviewList){
            reviewedBook.add(review.getBook());
        }

        // 사용자가 구매한 전체 book
        List<Book> unReviewedBooks = new ArrayList<>();
        for (Order order : orderList) {
            for (OrderItem items : order.getOrderItems()) {
                Book book = items.getBook();
                if (!reviewedBook.contains(book)) {
                    unReviewedBooks.add(book);
                }
            }
        }

        if (unReviewedBooks.isEmpty()) {
            // Handle the case where the user does not have any orders
            String errorMessage = "작성할 리뷰가 없습니다";
            model.addAttribute("errorMessage", errorMessage);
            return "myPage/review/reviewList";
        }

        model.addAttribute("books", unReviewedBooks);
        model.addAttribute("review", new Review());
        return "myPage/review/reviewForm";
    }


    @PostMapping("/add") // 작성한 리뷰 저장
    public String saveReview(@ModelAttribute("review") Review review, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "user/signIn";
        }
        User user = userService.getUser(userId);

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
