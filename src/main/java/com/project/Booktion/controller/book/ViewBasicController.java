package com.project.Booktion.controller.book;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Review;
import com.project.Booktion.model.User;
import com.project.Booktion.service.BookService;
import com.project.Booktion.service.ReviewService;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@SessionAttributes("book")
public class ViewBasicController {

    private final BookService bookService;
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") long bookId, Model model) {
        //책 상세페이지로 이동, 책이 없으면 noBook 페이지로 이동
        Book book = bookService.findByBookIdAndBookType(bookId, 1); // 북타입이 일반책인 것만으로 조건 추가
        if(book == null) {
            return "noBook";
        }
        model.addAttribute("book", book);
        model.addAttribute("bookForm", new BookForm()); // 새로운 BookForm 객체 생성


        // 추가: 해당 책의 후기 리스트를 가져와서 모델에 추가
        //List<Review> reviews = reviewService.getReviewsByBook(book);
//        if (reviews.isEmpty()) {
//            model.addAttribute("noReviews", true); // 후기가 없음을 표시하기 위한 속성 추가
//        } else {
//            model.addAttribute("reviews", reviews);
//        }

        // 리뷰 등록을 위한 빈 Review 객체도 모델에 추가
       // model.addAttribute("review", new Review());

        return "book/bookInfo";
    }

    @PostMapping("/{id}/review") // 추가: 리뷰 작성 처리를 위한 POST 요청 핸들러
    public String addReview(@RequestParam("bookId") long bookId, @ModelAttribute("review") Review review, BindingResult result, Model model, HttpSession session) {
        Book book = bookService.findByBookIdAndBookType(bookId, 1); // 일반책만 나오게
        if (book == null) {
            return "noBook";
        }

        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            // 로그인하지 않은 경우, 알림 메시지를 모델에 추가하고 책 상세 페이지로 리다이렉트
            model.addAttribute("alert", "리뷰를 작성하려면 로그인이 필요합니다.");
            return "redirect:/book/" + bookId;
        }

        // 리뷰 작성 처리 로직 추가
        review.setBook(book);
        User user = userService.getUserById(userId);
        review.setUser(user);
        reviewService.createReview(review);

        // 리뷰 작성 후 다시 해당 책 상세 페이지로 리다이렉트
        return "redirect:/book/" + bookId;
    }
}
