package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.Address;
import com.project.Booktion.model.Book;

import java.util.Date;

public class UsedBookOrder {
    //중고책 OrderForm command class
    private long usedBookId;
    private String buyerId;
    private Date orderDate;
    private String payment; //결제 방식
    private String shipMessage; //배송 메세지
    private Address address;
    private String card; //카드 선택시 카드명
    private String orderName; //배송받는 사람 이름
    private Book book;

    private String phoneNumber;
    public long getUsedBookId() {
        return usedBookId;
    }

    public void setUsedBookId(long usedBookId) {
        this.usedBookId = usedBookId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getShipMessage() {
        return shipMessage;
    }

    public void setShipMessage(String shipMessage) {
        this.shipMessage = shipMessage;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UsedBookOrder{" +
                "usedBookId=" + usedBookId +
                ", buyerId='" + buyerId + '\'' +
                ", orderDate=" + orderDate +
                ", payment='" + payment + '\'' +
                ", shipMessage='" + shipMessage + '\'' +
                ", address=" + address +
                ", card='" + card + '\'' +
                ", orderName='" + orderName + '\'' +
                ", book=" + book +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
