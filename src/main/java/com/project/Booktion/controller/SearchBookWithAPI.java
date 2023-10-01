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
        model.addAttribute("keyword", keyword);
        return "searchApi";
    }

    @PostMapping("search/selected")
    public String processForm(@RequestParam String selectedBook, @RequestParam String bookType,
                              @RequestParam String deliveryCompany, @RequestParam int price,
                              HttpSession session, Model model) {
        User user = userRepository.findByUserId((String)session.getAttribute("userId"));
        if(user == null){
            return "user/signIn";
        }
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
        return saved.getBook().getBookId();
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

}
