package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.UsedBook;
import com.project.Booktion.service.BookService;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/used")
@RequiredArgsConstructor
public class ViewUsedController {
    private final UsedBookService usedBookService;
    private final BookService bookService;

    @RequestMapping("/books/{bookId}")
    public String showBookInfo(@PathVariable long bookId, Model model){
        //책 상세페이지로 이동
        log.info("ViewUsedController#showBookInfo run! bookId : " + bookId);
        Book book = bookService.findById(bookId);
        if(book == null){
            return "noBook"; //상품이 없다는 페이지로 이동
        }
        UsedBook usedBook = usedBookService.getUsedBookByBookId(bookId);
        model.addAttribute("book", book);
        model.addAttribute("usedBook", usedBook);
        return "used/bookInfo";
    }

    @RequestMapping("/main")
    public String showUsedMain(Model model){
        //중고책 메인화면으로 이동
        List<Book> bookList = usedBookService.getAllUsedBookList(2);
        model.addAttribute("bookList", bookList);

        //새로 등록된 중고책 list
        List<Book> newBookList = usedBookService.getNewBookList(2);
        log.info("newBook data check : " + newBookList.toString());
        model.addAttribute("newBookList", newBookList);
        //특가 기회 list
        List<Book> saleBookList = usedBookService.getSaleBookList(2);
        log.info("saleBook data check : " + saleBookList.toString());
        model.addAttribute("saleBookList", saleBookList);
        return "used/usedMain";
    }

    @RequestMapping("/books/new/{isbn}")
    public String newBook(@PathVariable String isbn){
        //bookType이 1인 책 검색하기
        Book book = bookService.findByIsbnAndType(isbn, 1);
        if(book == null){
            return "noBook"; //상품이 없다는 페이지로 이동
        }
        return "redirect:/book/" + book.getBookId();
    }
}
