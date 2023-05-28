package com.project.Booktion.repository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository {
import com.project.Booktion.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByClientId(Long clientId);
}
