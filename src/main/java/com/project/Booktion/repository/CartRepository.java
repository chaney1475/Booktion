package com.project.Booktion.repository;

import com.project.Booktion.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByClientId(String clientId);
    Cart findByUserId(String userId);

}
