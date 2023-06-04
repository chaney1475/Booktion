package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TempOrder implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temp_order_seq_generator")
    @SequenceGenerator(name = "temp_order_seq_generator", sequenceName = "TEMP_ORDER_SEQ", allocationSize = 1)
    private Long tempOrderId;

    @Column
    private Long auctionBookId;

    @OneToOne
    @JoinColumn(name = "bid_id")
    private Bid bid;

    private String userId;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book bookInfo;

    // Constructors, getters and setters, etc.

    public TempOrder() {
    }

    public TempOrder(Long auctionBookId, Bid bid, String userId) {
        this.auctionBookId = auctionBookId;
        this.bid = bid;
        this.userId = userId;
    }

    // Getters and setters

    public Long getTempOrderId() {
        return tempOrderId;
    }

    public void setTempOrderId(Long tempOrderId) {
        this.tempOrderId = tempOrderId;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAuctionBookId() {
        return auctionBookId;
    }

    public void setAuctionBookId(Long auctionBookId) {
        this.auctionBookId = auctionBookId;
    }

    public Book getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(Book bookInfo) {
        this.bookInfo = bookInfo;
    }
}
