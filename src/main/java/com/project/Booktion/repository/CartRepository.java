package com.project.Booktion.repository;

import com.project.Booktion.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByClientId(Long clientId);
}
