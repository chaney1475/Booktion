package com.project.Booktion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="order")
public class Order {
    @Id
    private int orderId;
    private int userId;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    private String name;
    @Embedded
    private Address address;
    private String shipMessage;
    private int price;

    /*getter & setter*/
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getShipMessage() {
        return shipMessage;
    }

    public void setShipMessage(String shipMessage) {
        this.shipMessage = shipMessage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Order() {
    }
}
