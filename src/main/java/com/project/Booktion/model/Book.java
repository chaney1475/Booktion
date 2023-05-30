package com.project.Booktion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="book")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_generator")
    @SequenceGenerator(name = "book_seq_generator", sequenceName = "BOOK_SEQ")
    private long bookId;
    private int bookType;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("title")
    private String title;

    private String author;
    @JsonProperty("price")
    private int price;
    @JsonProperty("thumbnail")
    private String imageUrl;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("datetime")
    private Date pubDate;
    @JsonProperty("contents")
    private String description;

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
    @JsonSetter("authors")
    public void setAuthor(List<String> authorsList) {
        this.author = String.join(",", authorsList);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book() { }

    public Book(long bookId, int bookType, String isbn, String title, String author, int price, String imageUrl, String publisher, Date pubDate, User user, String description) {
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
        this.description = description;
    }
}
