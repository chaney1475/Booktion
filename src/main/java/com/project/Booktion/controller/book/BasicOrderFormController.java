package com.project.Booktion.controller.book;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Order;
import com.project.Booktion.model.OrderItem;
import com.project.Booktion.model.User;
import com.project.Booktion.service.BookService;
import com.project.Booktion.service.CartService;
import com.project.Booktion.service.OrderService;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j //로그 찍는 기능
@Controller
@RequestMapping("/book/order")
@RequiredArgsConstructor
@SessionAttributes("orderForm")
public class BasicOrderFormController {
    // 일반 책 주문 폼 컨트롤러
    private final BookService bookService;
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/form") //폼 생성
    public String newForm(@RequestParam(required = false, name = "quantity") Integer quantity, @RequestParam(required = false, name = "bookId") long bookId, HttpSession session, Model model) { //form
        String userId = (String) session.getAttribute("userId");
        System.out.println("userId: " + userId);
        User user = userService.getUser(userId);

        // 이전에 선택한 책 초기화
        OrderForm orderForm = (OrderForm) session.getAttribute("orderForm");
        if (orderForm != null) {
            orderForm.getBooks().clear();
        } else {
            orderForm = new OrderForm();
            session.setAttribute("orderForm", orderForm);
        }

        if (bookId > 0) { // 개별 책 선택
            Book book = bookService.findByBookIdAndBookType(bookId, 1); //일반책만 검색하게 수정
            if (book == null) { // 존재하지 않는 책을 선택한 경우
                model.addAttribute("error", "존재하지 않는 책입니다.");
                return "book/orderForm";
            }
            if (quantity == null || quantity <= 0) {
                quantity = 1;
            }
            BookForm bookForm = new BookForm(book);
            bookForm.setQuantity(quantity); // 이전 페이지에서 전달된 quantity 값을 설정
            bookForm.setPrice(book.getPrice() * quantity);

            orderForm.getBooks().add(bookForm);
            orderForm.getOrder().setUser(user);
            orderForm.getOrder().setOrderType(1);
            orderForm.getOrder().setPrice(bookForm.getPrice());

        } else {
            List<Book> cartItems = cartService.getCartItems();
            if (cartItems == null || cartItems.isEmpty()) {
                model.addAttribute("error", "장바구니가 비어있습니다.");
                return "book/orderForm";
            }

            List<Long> cartItemIds = cartService.getCartItemIds();
            List<BookForm> bookForms = new ArrayList<>();
            for (Book cartItem : cartItems) {
                BookForm bookForm = new BookForm(cartItem);
                bookForms.add(bookForm);
            }
            //OrderForm orderForm = new OrderForm(bookForms);
            orderForm.setSelectedCartItemIds(cartItemIds);
            orderForm.getOrder().setUser(user);

        }

        model.addAttribute("orderForm", orderForm);
        return "book/orderForm";
    }

    @PostMapping("/submit") //폼 제출
    public String submitOrderForm(@ModelAttribute("orderForm") OrderForm orderForm, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("error", "주문 정보에 오류가 있습니다."); // 에러 메시지 수정
            return "book/orderForm";
        }
        String userId = (String) session.getAttribute("userId");
        User user = userService.getUser(userId);

        System.out.println("orderForm: " + orderForm);
        System.out.println("orderForm: " + orderForm.getOrder().getName());

        OrderForm savedOrderForm = (OrderForm) session.getAttribute("orderForm"); // 이전에 세션에 저장된 orderForm 가져오기

        if (savedOrderForm != null) {
            // 이전 orderForm의 내용을 새로 제출된 orderForm에 복사
            orderForm.setOrder(savedOrderForm.getOrder());
            orderForm.setBooks(savedOrderForm.getBooks());
        }

        orderForm.getOrder().setUser(user);

        List<BookForm> bookForms = orderForm.getBooks();
        if (bookForms == null || bookForms.isEmpty()) {
            model.addAttribute("error", "주문할 책이 선택되지 않았습니다.");
            return "book/orderForm";
        }

        for (BookForm bookForm : bookForms) {
            Book book = bookService.findByBookIdAndBookType(bookForm.getBook().getBookId(), 1);
            if (book == null) {
                model.addAttribute("error", "존재하지 않는 책입니다.");
                return "book/orderForm";
            }
            bookForm.setBook(book);

        }
        orderForm.setBooks(bookForms);

//        List<BookForm> bookForms = new ArrayList<>();
//        for (Long bookId : selectedBooks) {
//            Book book = bookService.findByBookIdAndBookType(bookId, 1);
//            if (book == null) {
//                model.addAttribute("error", "존재하지 않는 책입니다.");
//                return "book/orderForm";
//            }
//            BookForm bookForm = new BookForm();
//            bookForm.setBook(book);
//            // 다른 필요한 정보도 설정할 수 있습니다.
//            bookForms.add(bookForm);
//        }
//
//        orderForm.setBooks(bookForms);

        Order order = orderService.createOrderFromForm(orderForm, user);

        if (order == null) {
            model.addAttribute("error", "주문 생성에 실패하였습니다.");
            return "book/orderForm";
        }

        // 세션에 새로운 orderForm 저장
        session.setAttribute("orderForm", orderForm);

        //cartService.updateCartSession(session); // 주문 처리 후 카트 세션 정보 업데이트

        return "redirect:/book/order/success?orderId=" + order.getOrderId();
    }

    @GetMapping("/success")
    public String showOrderSuccess(@RequestParam("orderId") long orderId, Model model) {
        Order order = orderService.findByOrderId(orderId);
        List<OrderItem> orderItems = order.getOrderItems();

        System.out.println(orderItems.get(0).getBook().getTitle());
        model.addAttribute("orderItems", orderItems);
        if (order == null) {
            model.addAttribute("error", "주문 정보를 찾을 수 없습니다.");
            return "book/orderForm";
        }
        model.addAttribute("order", order);
        return "book/orderSuccess";
    }

}
