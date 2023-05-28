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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public String newForm(@RequestParam(required=false, name="bookId") long bookId, Model model) { //form
        if (bookId != 0) { // 개별 책 선택
            Book book = bookService.findByIdAndBookType(bookId, 0); //일반책만 검색하게 수정
            if (book == null) { // 존재하지 않는 책을 선택한 경우
                model.addAttribute("error", "존재하지 않는 책입니다.");
                return "orderForm";
            }
            List<BookForm> bookForms = new ArrayList<>();
            bookForms.add(new BookForm(book));
            OrderForm orderForm = new OrderForm(bookForms);
            model.addAttribute("orderForm", orderForm);
        } else { // 장바구니에서 책을 선택한 경우
            List<Book> cartItems = cartService.getCartItems();
            if (cartItems == null || cartItems.isEmpty()) { // 비어있는 장바구니인 경우
                model.addAttribute("error", "장바구니가 비어있습니다.");
                return "orderForm";
            }
            List<BookForm> bookForms = new ArrayList<>();
            for (Book cartItem : cartItems) {
                bookForms.add(new BookForm(cartItem));
            }
            OrderForm orderForm = new OrderForm(bookForms);
            model.addAttribute("orderForm", orderForm);
        }
        return "orderForm";
    }

    @PostMapping("/{bookId}") //폼 제출
    public String submitOrderForm(@PathVariable long bookId, @ModelAttribute("orderForm") OrderForm orderForm, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("book", bookService.findByIdAndBookType(bookId, 0));
            return "orderForm";
        }

        Book book = bookService.findByIdAndBookType(bookId, 0);
        if (book == null) {
            model.addAttribute("error", "존재하지 않는 책입니다.");
            return "orderForm";
        }

        Order order = new Order();
        List<BookForm> bookForms = orderForm.getBooks();
        orderForm.calculateTotalOrderPrice();
        // 선택된 책들의 가격 합산
        model.addAttribute("orderedBooks", bookForms);

        orderService.createOrder(order);

        List<Book> cartItems = cartService.getCartItems();
        if (cartItems != null && !cartItems.isEmpty()) {
            // 선택된 책들을 장바구니에서 삭제
            for (BookForm bookForm : bookForms) {
                cartService.removeCartItem(bookForm.getBook().getBookId());
            }
        }
        // 주문 처리 완료 후, 카트 세션 정보 업데이트
        cartService.updateCartSession(session);

        return "redirect:/book/order/success?orderId=" + order.getOrderId();
        //return null;
    }

    @GetMapping("/success")
    public String showOrderSuccess(@RequestParam("orderId") long orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            model.addAttribute("error", "주문 정보를 찾을 수 없습니다.");
            return "orderForm";
        }
        model.addAttribute("order", order);
        return "orderSuccess";
    }

}
