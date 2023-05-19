package com.project.Booktion.controller.book;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Review;
import com.project.Booktion.service.BookService;
import com.project.Booktion.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@SessionAttributes("book")
public class ViewBasicController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") String bookId, Model model) {
        //책 상세페이지로 이동, 책이 없으면 noBook 페이지로 이동
        Book book = bookService.findById(bookId);
        if(book == null) {
            return "noBook";
        }
        model.addAttribute("book", book);

        // 추가: 해당 책의 후기 리스트를 가져와서 모델에 추가
        List<Review> reviews = reviewService.getReviewsByBook(book);
        model.addAttribute("reviews", reviews);

        return "bookInfo";
    }

    @PostMapping("/review") // 추가: 리뷰 작성 처리를 위한 POST 요청 핸들러
    public String addReview(@RequestParam("bookId") String bookId, @ModelAttribute("review") Review review, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            // 에러 처리 로직 추가
//        }

        Book book = bookService.findById(bookId);
        if (book == null) {
            return "noBook";
        }

        // 추후에 로그인한 후 리뷰 작성할 수 있도로 수정 필요
        // 리뷰 작성 처리 로직 추가
        review.setBook(book);
        reviewService.createReview(review);

        // 리뷰 작성 후 다시 해당 책 상세 페이지로 리다이렉트
        return "redirect:/book/" + bookId;
    }
}
