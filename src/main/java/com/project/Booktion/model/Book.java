package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_generator")
    @SequenceGenerator(name = "book_seq_generator", sequenceName = "BOOK_SEQ")
    private long bookId;
    private int bookType;
    private String isbn;
    private String title;
    private String author;
    private int price;
    private String imageUrl;
    private String publisher;
    private Date pubDate;
    @ManyToOne //다대일 관계가 맞을까.. 한방향으로 해도 될까..
    @JoinColumn(name = "seller_id")
    private User user;

    /*getter & setter*/
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book() { }

    public Book(long bookId, int bookType, String isbn, String title, String author, int price, String imageUrl, String publisher, Date pubDate, User user) {
        this.bookId = bookId;
        this.bookType = bookType;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.imageUrl = imageUrl;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.user = user;
    }


}
