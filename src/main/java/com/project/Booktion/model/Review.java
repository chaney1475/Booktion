package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="review")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_generator")
    @SequenceGenerator(name = "review_seq_generator", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    @Column(name="review_id")
    private long reviewId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    private String title;

    @Column
    private String contents;

    @Column(name="create_date")
    private LocalDateTime createDate;

    // Constructors, getters and setters, etc.

    public Review() {
    }

    public Review(long reviewId, User user, Book book, String title, String contents, LocalDateTime createDate) {
        this.reviewId = reviewId;
        this.userId = user;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
