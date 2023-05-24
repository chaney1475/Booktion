package com.project.Booktion.controller.book;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Order;
import com.project.Booktion.service.BookService;
import com.project.Booktion.service.CartService;
import com.project.Booktion.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //로그 찍는 기능
@Controller
@RequestMapping("/book/order")
@RequiredArgsConstructor
@SessionAttributes("order")
public class BasicOrderFormController {
    // 일반 책 주문 폼 컨트롤러
    private final BookService bookService;
    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping("/form") //폼 생성
    public String newForm(@RequestParam(required=false, name="bookId") String bookId, Model model) { //form
        if (bookId != null) { // 개별 책 선택
            Book book = bookService.findById(bookId);
            if (book == null) { // 존재하지 않는 책을 선택한 경우
                model.addAttribute("error", "존재하지 않는 책입니다.");
                return "orderForm";
            }
            model.addAttribute("book", book);
        } else { // 장바구니에서 책을 선택한 경우
            List<Book> cartItems = cartService.getCartItems();
            if (cartItems == null || cartItems.isEmpty()) { // 비어있는 장바구니인 경우
                model.addAttribute("error", "장바구니가 비어있습니다.");
                return "orderForm";
            }
            model.addAttribute("cartItems", cartItems);
        }
        model.addAttribute("orderForm", new OrderForm());
        return "orderForm";
    }

    @PostMapping("/{bookId}") //폼 제출
    public String submitOrderForm(@PathVariable String bookId, @ModelAttribute("orderForm") OrderForm orderForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", bookService.findById(bookId));
            return "orderForm";
        }

        Book book = bookService.findById(bookId);
        if (book == null) {
            model.addAttribute("error", "존재하지 않는 책입니다.");
            return "orderForm";
        }

        Order order = new Order();
       // order.setBook(book);
        orderService.createOrder(order);

        //return "redirect:/book/order/success?id=" + order.getId();
        return null;
    }

    @GetMapping("/success")
    public String showOrderSuccess(@RequestParam("id") String orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            model.addAttribute("error", "주문 정보를 찾을 수 없습니다.");
            return "orderForm";
        }
        model.addAttribute("order", order);
        return "orderSuccess";
    }

}
