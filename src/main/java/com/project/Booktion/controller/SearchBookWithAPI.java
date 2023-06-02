package com.project.Booktion.controller;

import com.project.Booktion.model.*;
import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.repository.UsedBookRepository;
import com.project.Booktion.repository.UserRepository;
import com.project.Booktion.service.BookApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SearchBookWithAPI {
    private final UserRepository userRepository;
    final private BookApiService bookApiService;
    private final AuctionBookRepository auctionBR;
    private final UsedBookRepository usedBR;
    @GetMapping("search/{bookType}")
    public String searchBook( Model model, @PathVariable String bookType) {
        model.addAttribute("bookType",bookType);
        return "searchApi";
    }
    @PostMapping("search/{bookType}")
    public String searchBook(@RequestParam String keyword, Model model, @PathVariable String bookType) {
        model.addAttribute("bookType", bookType);
        List<Book> books = bookApiService.getBooksByTitle(keyword);
        model.addAttribute("books", books);
        return "searchApi";
    }

    @PostMapping("auction/selected")
    public String processForm(@RequestParam String selectedBook, @RequestParam String bookType,
                              @RequestParam String deliveryCompany, @RequestParam int price,
                              HttpSession session, Model model) {
        User user = userRepository.findByUserId((String)session.getAttribute("userId"));
        String[] parts = selectedBook.split(" ");
        String isbn = parts[0];
        Book book = bookApiService.getBookByISBN(isbn);
        System.out.println(book.getIsbn() + " , " + book.getTitle());
        book.setUser(user);

        if(bookType.equals("auction")){
            Long savedId = registAuction(deliveryCompany, price, book);
            model.addAttribute("savedId", savedId);

            return "redirect:/auction/books/" + savedId;

        } else if (bookType.equals("used")) {
            Long savedId = registUsed(deliveryCompany, price, book);
            model.addAttribute("savedId", savedId);

            return "redirect:/used/books/" + savedId;

        }else {
            System.out.println("bookType 오류");
            return "noBook";
        }

    }

    private Long registUsed(String deliveryCompany, int price, Book bookByISBN) {
        bookByISBN.setPrice(price);
        bookByISBN.setBookType(2);
        UsedBook usedBook = new UsedBook();
        usedBook.setBook(bookByISBN);

        usedBook.setStatus(0);
        usedBook.setShippingCompany(deliveryCompany);

        UsedBook saved = usedBR.save(usedBook);
        return saved.getUsedBookId();
    }

    private Long registAuction(String deliveryCompany, int price, Book bookByISBN) {
        bookByISBN.setBookType(3);
        AuctionBook auctionBook = new AuctionBook();
        auctionBook.setBook(bookByISBN);

        auctionBook.setShippingCompany(deliveryCompany);
        auctionBook.setStatus(0);
        auctionBook.setStartPrice(price);
        AuctionBook saved = auctionBR.save(auctionBook);

        return saved.getAuctionBookId();
    }
    Book getBookByISBN(List<Book> books, String isbn){
        isbn = "1234567890"; // 찾고자 하는 ISBN 값
        String finalIsbn = isbn;
        Book foundBook = books.stream()
                .filter(book -> book.getIsbn().equals(finalIsbn))
                .findFirst()
                .orElse(null);
        if (foundBook != null) {
            // 해당 ISBN에 해당하는 책을 찾은 경우에 수행할 작업을 추가합니다.
            System.out.println("ISBN: " + foundBook.getIsbn());
            System.out.println("Title: " + foundBook.getTitle());
            return foundBook;
        } else {
            System.out.println("해당 ISBN에 해당하는 책을 찾지 못했습니다.");
        }
        return null;
    }


}
