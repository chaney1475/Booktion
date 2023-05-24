package com.project.Booktion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reviewId;

    @Column
    private String clientId;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private Date createDate;

    // Constructors, getters and setters, etc.

    public Review() {
    }

    public Review(String clientId, Book book, String title, String contents, Date createDate) {
        this.clientId = clientId;
        this.book = book;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
    }

    // Getters and setters

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
