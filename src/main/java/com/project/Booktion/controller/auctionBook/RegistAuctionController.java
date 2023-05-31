package com.project.Booktion.controller.auctionBook;


import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.UsedBook;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.repository.UsedBookRepository;
import com.project.Booktion.repository.UserRepository;
import com.project.Booktion.service.AuctionBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

@Slf4j // 로그 찍는 기능
@RequiredArgsConstructor
public class RegistAuctionController {

    private final AuctionBookRepository auctionBR;
    private final UsedBookRepository usedBR;
    private final UserRepository userRepository;
    //@PostMapping("/selected")
    public String processForm(@ModelAttribute("books") List<Book> books,
                              @RequestParam String selectedBook, @RequestParam String bookType,
                              @RequestParam String deliveryCompany, @RequestParam int price,
                              HttpSession session, Model model) {
        System.out.println("selectedISBN = " + selectedBook + ", bookType = " + bookType + ", deliveryCompany = " + deliveryCompany + ", price = " + price + ", session = " + session + ", model = " + model);
        System.out.println("RegistAuctionController.processForm");
        //user 객체 받아오기
        User user = userRepository.findByUserId((String)session.getAttribute("userId"));
        //books 받아오기
        for (Book book : books) {
            System.out.println(book.getTitle());
            System.out.println(book.getIsbn());
        }
        Book bookByISBN = getBookByISBN(books, selectedBook);
        System.out.println("bookByISBN");
        System.out.println(bookByISBN.getIsbn() + " , " + bookByISBN.getTitle());

        bookByISBN.setUser(user);

        if(bookType == "used"){
            Long savedId = registAuction(deliveryCompany, price, bookByISBN);
            model.addAttribute("savedId", savedId);

            return "redirect:/auction/books/" + savedId;

        } else if (bookType == "auction") {
            Long savedId = registUsed(deliveryCompany, price, bookByISBN);
            model.addAttribute("savedId", savedId);

            return "redirect:/used/books/" + savedId;

        }else {
            System.out.println("bookType 오류");
            return "noBook";
        }

    }

    private Long registUsed(String deliveryCompany, int price, Book bookByISBN) {
        bookByISBN.setPrice(price);
        bookByISBN.setBookType(3);
        UsedBook usedBook = new UsedBook();
        usedBook.setBook(bookByISBN);

        usedBook.setStatus(0);
        usedBook.setShippingCompany(deliveryCompany);

        UsedBook saved = usedBR.save(usedBook);
        return saved.getUsedBookId();
    }

    private Long registAuction(String deliveryCompany, int price, Book bookByISBN) {
        bookByISBN.setBookType(2);
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
