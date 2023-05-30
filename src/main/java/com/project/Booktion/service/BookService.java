package com.project.Booktion.service;

import com.project.Booktion.model.Book;
import com.project.Booktion.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book findById(String bookId) {
        return null;
    }

    public List<Book> findAll() {
        return null;
    }

    public Book findByBookIdAndBookType(long bookId, int i) {
        return bookRepository.findByBookIdAndBookType(bookId, i);
    }
}
