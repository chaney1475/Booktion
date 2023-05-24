package com.project.Booktion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "used_book")
public class UsedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long usedBookId;

    @Column
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    // Constructors, getters and setters, etc.

    public UsedBook() {
    }

    public UsedBook(Integer status, Book book) {
        this.status = status;
        this.book = book;
    }

    // Getters and setters

    public Long getUsedBookId() {
        return usedBookId;
    }

    public void setUsedBookId(Long usedBookId) {
        this.usedBookId = usedBookId;
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
