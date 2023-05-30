package com.project.Booktion.controller;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Cart;
import com.project.Booktion.model.CartItem;
import com.project.Booktion.repository.BookRepository;
import com.project.Booktion.repository.CartItemRepository;
import com.project.Booktion.repository.CartRepository;
import com.project.Booktion.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("{cartId}/add")
    public String  addCart(@PathVariable long cartId, @RequestBody CartItem cartItem, Model model) {

        cartService.addCartItem(cartId, cartItem, model);
        return "cart";
    }

    @DeleteMapping("/{cartItemId}/remove")
    public String removeCart(@PathVariable long cartItemId) {

        cartService.removeCartItem(cartItemId);
        return "cart";
    }

    @PutMapping("/{cartItemId}/quantity")
    public String updateCartItemQuantity(@PathVariable long cartItemId, @RequestParam int quantity) {
        cartService.updateCartItemQuantity(cartItemId, quantity);
        return "cart";
    }

}
