package com.project.Booktion.controller.book;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Order;
import com.project.Booktion.model.OrderItem;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.UserRepository;
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
@SessionAttributes("order")
public class BasicOrderFormController {
    // 일반 책 주문 폼 컨트롤러
    private final BookService bookService;
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/form") //폼 생성
    public String newForm(@RequestParam(required = false, name = "quantity") Integer quantity, @RequestParam(required = false, name = "bookId") long bookId, HttpSession session, Model model) { //form
        String userId = (String) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        if (bookId > 0) { // 개별 책 선택
            Book book = bookService.findByBookIdAndBookType(bookId, 1); //일반책만 검색하게 수정
            if (book == null) { // 존재하지 않는 책을 선택한 경우
                model.addAttribute("error", "존재하지 않는 책입니다.");
                return "book/orderForm";
            }
            if (quantity == null || quantity <= 0) {
                // 수량이 비어있거나 0 이하인 경우에 대한 처리 로직
                // 예를 들어, 기본값을 설정하거나 오류 메시지를 전달할 수 있습니다.
                quantity = 1; // 기본값 설정
            }
            List<BookForm> bookForms = new ArrayList<>();
            BookForm bookForm = new BookForm(book);
            bookForm.setQuantity(quantity); // 이전 페이지에서 전달된 quantity 값을 설정
            bookForms.add(bookForm);

            // 선택된 책들을 세션에 저장
            session.setAttribute("selectedBooks", bookForms);

            System.out.println("quantity: " + bookForm.getQuantity());

            OrderForm orderForm = new OrderForm(bookForms);
            orderForm.getOrder().setUser(user);
            model.addAttribute("orderForm", orderForm);

            System.out.println("orderForm: " + orderForm);
            System.out.println("orderForm: " + orderForm.getBooks().isEmpty());

        } else { // 장바구니에서 책을 선택한 경우
            List<Book> cartItems = cartService.getCartItems();
            if (cartItems == null || cartItems.isEmpty()) { // 비어있는 장바구니인 경우
                model.addAttribute("error", "장바구니가 비어있습니다.");
                return "book/orderForm";
            }

            List<Long> cartItemIds = cartService.getCartItemIds();
            List<BookForm> bookForms = new ArrayList<>();
            for (Book cartItem : cartItems) {
                BookForm bookForm = new BookForm(cartItem);
                bookForms.add(bookForm);
            }
            OrderForm orderForm = new OrderForm(bookForms);
            orderForm.setSelectedCartItemIds(cartItemIds); // 선택한 카트 아이템 아이디 리스트 설정
            orderForm.getOrder().setUser(user);
            model.addAttribute("orderForm", orderForm);

        }


        return "book/orderForm";
    }

    @PostMapping("/submit") //폼 제출
    public String submitOrderForm(@ModelAttribute("orderForm") OrderForm orderForm, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("error", "주문 정보에 오류가 있습니다."); // 에러 메시지 수정
            return "book/orderForm";
        }
        System.out.println("orderForm: " + orderForm);
        System.out.println("orderForm: " + orderForm.getOrder());
        System.out.println("orderForm: " + orderForm.getBooks());
        System.out.println("orderForm: " + orderForm.getOrder().getName());
        String userInputName = orderForm.getOrder().getName();
        orderForm.getOrder().setName(userInputName);

        System.out.println("orderForm: " + orderForm.getOrder().getName());
        // 세션에서 선택된 책들을 가져옴
        List<Long> selectedBooks = orderForm.getSelectedBooks();
        if (selectedBooks.isEmpty()) {
            model.addAttribute("error", "주문할 책이 선택되지 않았습니다.");
            return "book/orderForm";
        }

        for (Long bookId : selectedBooks) {
            Book book = bookService.findByBookIdAndBookType(bookId, 1);
            System.out.println("주문책: " + book.getTitle());
            if (book == null) {
                model.addAttribute("error", "존재하지 않는 책입니다.");
                return "book/orderForm";
            }
        }

        Order order = orderService.createOrderFromForm(orderForm, (User) session.getAttribute("user"));
        if (order == null) {
            model.addAttribute("error", "주문 생성에 실패하였습니다.");
            return "book/orderForm";
        }

        cartService.updateCartSession(session); // 주문 처리 후 카트 세션 정보 업데이트

        return "redirect:/book/order/success?orderId=" + order.getOrderId();
    }

    @GetMapping("/success")
    public String showOrderSuccess(@RequestParam("orderId") long orderId, Model model) {
        Order order = orderService.findByOrderId(orderId);
        if (order == null) {
            model.addAttribute("error", "주문 정보를 찾을 수 없습니다.");
            return "book/orderForm";
        }
        model.addAttribute("order", order);
        return "book/orderSuccess";
    }

}
