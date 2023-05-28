package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auction_book")
public class AuctionBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auction_book_seq_generator")
    @SequenceGenerator(name = "auction_book_seq_generator", sequenceName = "AUCTION_BOOK_SEQ")
    @Column
    private long auctionBookId;

    @Column
    private double startPrice;

    @Column
    private int status;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    @Column
    private String shippingCompany;

    // Constructors, getters and setters, etc.

    public AuctionBook() {
    }

    public AuctionBook(long auctionBookId, double startPrice, int status, Book book) {
        this.auctionBookId = auctionBookId;
        this.startPrice = startPrice;
        this.status = status;
        this.book = book;
    }

    // Getters and setters

    public long getAuctionBookId() {
        return auctionBookId;
    }

    public void setAuctionBookId(long auctionBookId) {
        this.auctionBookId = auctionBookId;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }
}
