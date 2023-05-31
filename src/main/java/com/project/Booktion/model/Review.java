package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="review")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_generator")
    @SequenceGenerator(name = "review_seq_generator", sequenceName = "REVIEW_SEQ")
    @Column
    private long reviewId;

    @Column
    @JoinColumn(name = "client_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
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

    public Review(long reviewId, User user, Book book, String title, String contents, Date createDate) {
        this.reviewId = reviewId;
        this.user = user;
        this.book = book;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
    }

    // Getters and setters

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
