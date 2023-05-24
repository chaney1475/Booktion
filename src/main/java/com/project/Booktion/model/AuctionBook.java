package com.project.Booktion.model;

import javax.persistence.*;

@Entity
@Table(name = "auction_book")
public class AuctionBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long auctionBookId;

    @Column
    private Double startPrice;

    @Column
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    // Constructors, getters and setters, etc.

    public AuctionBook() {
    }

    public AuctionBook(Double startPrice, Integer status, Book book) {
        this.startPrice = startPrice;
        this.status = status;
        this.book = book;
    }

    // Getters and setters

    public Long getAuctionBookId() {
        return auctionBookId;
    }

    public void setAuctionBookId(Long auctionBookId) {
        this.auctionBookId = auctionBookId;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
