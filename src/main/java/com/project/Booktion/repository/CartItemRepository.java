package com.project.Booktion.repository;

import com.project.Booktion.model.Cart;
import com.project.Booktion.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteByCart(Cart cart);
}
