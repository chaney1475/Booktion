package com.project.Booktion.service;

import com.project.Booktion.model.Book;

import java.util.ArrayList;
import java.util.List;

import com.project.Booktion.model.Cart;
import com.project.Booktion.model.CartItem;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.BookRepository;
import com.project.Booktion.repository.CartItemRepository;
import com.project.Booktion.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import com.project.Booktion.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CartService {
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final HttpSession session;

    public List<CartItem> getCartItems(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            return cart.getCartItemList();
        }
        return new ArrayList<>();
    }

    public boolean isBookInCart(String userId, long bookId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            List<CartItem> cartItems = cart.getCartItemList();
            for (CartItem cartItem : cartItems) {
                if (cartItem.getBook().getBookId() == bookId) {
                    return true;
                }
            }
        }
        return false;
    }

//    public User getUser(String userId) {
//        return userRepository.findByUserId(userId);
//    }
//
//    public Cart getCartByUserId(String userId) {
//        return cartRepository.findByUserId(userId);
//    }
}
