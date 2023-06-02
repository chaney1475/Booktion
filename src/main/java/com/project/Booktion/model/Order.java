package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_generator")
    @SequenceGenerator(name = "order_seq_generator", sequenceName = "ORDER_SEQ", allocationSize = 1)
    @Column(name="order_id")
    private long orderId;
    @ManyToOne
    @JoinColumn(name="client_id")
    private User user;
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;
    private String name;
    @Embedded
    private Address address;
    @Column(name = "ship_message")
    private String shipMessage;
    private int price;
    private String payment;
    private String card;
    @Column(name = "order_type")
    private int orderType;
    private int status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(name = "phone_number")
    private String phoneNumber;

    /*getter & setter*/
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user;}

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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);  // OrderItem과의 양방향 관계 설정
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(orderItem);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
      
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", shipMessage='" + shipMessage + '\'' +
                ", price=" + price +
                ", payment='" + payment + '\'' +
                ", card='" + card + '\'' +
                ", orderType=" + orderType +
                ", status='" + status + '\'' +
                ", orderItems=" + orderItems +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}