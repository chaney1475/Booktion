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
    private final CartItemRepository cartItemRepository;

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

        for (CartItem cartItem : cart.getCartItemList()) {
            System.out.println(cartItem.getBook().getBookId());
        }
        model.addAttribute("cartItems", cart.getCartItemList());

        return "cart/cartList";
    }

    @PostMapping("/add")
    public String addCart(@RequestParam("bookId") long bookId, @RequestParam("cartQuantity") int cartQuantity,
                          HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
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
        if(cart == null) {
            cart = new Cart(userId);
            cartRepository.save(cart);
        }
        Book book = bookRepository.findByBookId(bookId);

        if (cartService.isBookInCart(userId, bookId)) {
            model.addAttribute("errorMessage", "이미 카트에 존재하는 책입니다.");
            return "redirect:/book/" + bookId; // Redirect back to the book details page
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setBook(book);
        cartItem.setQuantity(cartQuantity);

        cartItemRepository.save(cartItem);

        cart.getCartItemList().add(cartItem);
        cartRepository.save(cart);

        model.addAttribute("successMessage", "도서가 장바구니에 추가되었습니다.");

        return "redirect:/cart";
    }
    @PostMapping("/delete")
    public String deleteCartItem(@RequestParam("itemId") Long itemId, Model model) {
        CartItem cartItem = cartItemRepository.findById(itemId).orElse(null);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            model.addAttribute("successMessage", "도서가 장바구니에서 삭제되었습니다.");
        }
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCartItem(@RequestParam("itemId") Long itemId, @RequestParam("cartQuantity") int cartQuantity, RedirectAttributes redirectAttributes) {
        CartItem cartItem = cartItemRepository.findById(itemId).orElse(null);
        if (cartItem != null) {
            if (cartQuantity <= 0) {
                redirectAttributes.addFlashAttribute("errorMessage", "수량은 1 이상이어야 합니다.");
            } else {
                cartItem.setQuantity(cartQuantity);
                cartItemRepository.save(cartItem);
                redirectAttributes.addFlashAttribute("successMessage", "수량이 업데이트되었습니다.");
            }
        }
        return "redirect:/cart";
    }


}
