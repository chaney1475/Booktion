package com.project.Booktion.repository;
import org.springframework.stereotype.Repository;

import com.project.Booktion.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByClientId(Long clientId);
}
