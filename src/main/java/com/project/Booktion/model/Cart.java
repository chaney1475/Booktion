package com.project.Booktion.model;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long cartId;

    @Column
    private String clientId;

    // Constructors, getters and setters, etc.

    public Cart() {
    }

    public Cart(String clientId) {
        this.clientId = clientId;
    }

    // Getters and setters

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
