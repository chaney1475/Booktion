package com.project.Booktion.controller.auctionBook;


import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.repository.UserRepository;
import com.project.Booktion.service.AuctionBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction")
@RequiredArgsConstructor
public class RegistAuctionController {

    private final AuctionBookRepository auctionBR;
    private final UserRepository userRepository;

    @PostMapping("/selected")
    public String processForm(@ModelAttribute String selectedISBN, @RequestParam int bookType,
                              @RequestParam String deliveryCompany, @RequestParam int price,
                              HttpServletRequest request
                              ,Model model) {

        List<Book> books = (List<Book>) model.getAttribute("books");
        Book bookByISBN = getBookByISBN(books, selectedISBN);

        bookByISBN.setBookType(bookType);
        User user = userRepository.findByUserId((String) request.getAttribute("userId"));
        if(user != null){
            bookByISBN.setUser(user);
        }else{
            System.out.println("user error: no user");
        }

        AuctionBook auctionBook = new AuctionBook();
        auctionBook.setBook(bookByISBN);

        auctionBook.setShippingCompany(deliveryCompany);
        auctionBook.setStatus(0);
        auctionBook.setStartPrice(price);

        AuctionBook saved = auctionBR.save(auctionBook);

        model.addAttribute("saved", saved);

        long bookId = auctionBook.getAuctionBookId();
        return "redirect:/auction/books/" + bookId;

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
