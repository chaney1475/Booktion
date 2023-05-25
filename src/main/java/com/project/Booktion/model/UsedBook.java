package com.project.Booktion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "used_book")
public class UsedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long usedBookId;

    @Column
    private int status;

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

    public long getUsedBookId() {
        return usedBookId;
    }

    public void setUsedBookId(long usedBookId) {
        this.usedBookId = usedBookId;
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
}
