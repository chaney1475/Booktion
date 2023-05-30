package com.project.Booktion.repository;

import com.project.Booktion.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndCartItemId(Long cartId, Long cartItemId);

    void deleteAllById(long cartItemId);
}
