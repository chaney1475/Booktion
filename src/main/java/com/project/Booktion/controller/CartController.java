package com.project.Booktion.controller;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Cart;
import com.project.Booktion.model.CartItem;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.BookRepository;
import com.project.Booktion.repository.CartItemRepository;
import com.project.Booktion.repository.CartRepository;
import com.project.Booktion.service.CartService;
import com.project.Booktion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    @GetMapping
    public String viewCartList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "user/signIn";
        }

        String userId = (String) session.getAttribute("userId");
        User user = userService.getUser(userId);
        if (user == null) {
            return "user/signIn";
        }

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            return "cart/noCart";
        }

        List<CartItem> cartItemList = cart.getCartItemList();
        for (CartItem cartItem : cartItemList) {
            System.out.println(cartItem.getBook().getBookId());
        }
        List<CartItem> cartItems = cartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);

        return "cart/cartList";
    }

    @PostMapping("/add")
    public String addCart(@RequestParam("bookId") long bookId, @RequestParam("quantity") int quantity,
                          HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "user/signIn";
        }

        String userId = (String) session.getAttribute("userId");
        User user = userService.getUser(userId);
        if (user == null) {
            return "user/signIn";
        }

        Book book = bookRepository.findByBookId(bookId);
        Cart cart = cartRepository.findByUserId(userId);
        if(cart == null) {
            cart = new Cart(userId);
            cartRepository.save(cart);
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);

        cart.getCartItemList().add(cartItem);
        cartRepository.save(cart);

        redirectAttributes.addFlashAttribute("successMessage", "도서가 장바구니에 추가되었습니다.");

        return "redirect:/cart";
    }


}
