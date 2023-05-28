package com.project.Booktion.controller;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Cart;
import com.project.Booktion.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    public String cartList(Model model){
        List<Book> carts = cartService.findAll(); // 카트에 담긴 책 리스트를 보여줌
        // 여기서 findAll이 맞는지 모르겠어요..

        //@PostMapping("/cart/{clientId}/{bookId}")
        //public String addCartItem(@PathVariable("clientId") Integer clientId, )

        if(carts.isEmpty()) {
            model.addAttribute("error", "장바구니가 비어있습니다.");
            return "cart";
        }
        model.addAttribute("carts", carts);
        return "order"; // 주문화면으로 넘어가기
    }

}
