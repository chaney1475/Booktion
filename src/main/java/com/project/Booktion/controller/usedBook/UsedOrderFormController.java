package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.UsedBook;
import com.project.Booktion.model.User;
import com.project.Booktion.service.BookService;
import com.project.Booktion.service.UsedBookService;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/used/order")
@RequiredArgsConstructor
public class UsedOrderFormController {
    private final UsedBookService usedBookService;
    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/{usedBookId}")
    public String newForm(@PathVariable long usedBookId,
                          @RequestParam long bookId,
                          HttpSession session,
                          @ModelAttribute("usedBookOrder") UsedBookOrder usedBookOrder){
        //중고 주문폼 생성
        log.info("UserOrderFormController#newFrom is run : \n " +
                "usedBookId :" + usedBookId + " bookId : " + bookId);
        //usedBook status 확인 후 판매 불가시 다시 화면으로 돌아감
        UsedBook usedBook = usedBookService.getUsedBookById(usedBookId);
        if(usedBook.getStatus() == 1){
            //구매 불가 상태
            return "redirect:/used/main";
        }
        //로그인 상태 확인
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {
            return "/user/signIn";
        }
        //판매자 본인 확인
        Book book = bookService.getBookById(bookId);
        if(userId.equals(book.getUser().getUserId())){
            return "redirect:/used/main";
        }
        User user = userService.getUser(userId);
        usedBookOrder.setBuyerId(userId);
        usedBookOrder.setUsedBookId(usedBookId);
        usedBookOrder.setBook(bookService.findById(bookId));
        session.setAttribute("address", user.getAddress());
        session.setAttribute("name", user.getName());
        session.setAttribute("phoneNumber", user.getPhoneNumber());
        return "used/orderForm";
    }

    @PostMapping("/submit")
    public String addForm(@RequestParam("bookId") long bookId,
                          HttpSession session,
                          @ModelAttribute("usedBookOrder") UsedBookOrder usedBookOrder){
        //중고 주문폼 제출
        log.info("usedBookOrder info : " + usedBookOrder.toString());
        usedBookService.submitOrderForm(bookId, usedBookOrder);
        return "redirect:/myPage/order/used";//주문완료 후 마이페이지로 수정해야됨
    }
}