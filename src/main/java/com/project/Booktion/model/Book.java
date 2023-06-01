package com.project.Booktion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.Booktion.service.IsbnDeserializer;

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
    @SequenceGenerator(name = "book_seq_generator", sequenceName = "BOOK_SEQ", allocationSize = 1)
    @Column(name = "book_id")
    private long bookId;
    @Column(name = "isbn")
    @JsonProperty("isbn")
    private String isbn;

    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "book_type")
    private int bookType;

    @Column(name = "price")
    @JsonProperty("price")
    private int price;

    @Column(name = "image_url")
    @JsonProperty("thumbnail")
    private String imageUrl;

    @Column(name = "description")
    @JsonProperty("contents")
    private String description;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "pub_date")
    @JsonProperty("datetime")
    private Date pubDate;

    @ManyToOne
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

    public Book(long bookId, String isbn, String title, String author, int bookType, int price, String imageUrl, String description, String publisher, Date pubDate, User user) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.bookType = bookType;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.user = user;
    }
}
