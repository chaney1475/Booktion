package com.project.Booktion.repository;

import com.project.Booktion.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookId(long bookId);

    Book findByBookIdAndBookType(long bookId, int bookType); // 북아이디로만 하면 중복이 있을 수 있어서. 북타입 조건 추가

    List<Book> findByBookType(int bookType);
    List<Book> findByBookTypeAndTitleContaining(int bookType, String query);

    List<Book> findTop4ByBookTypeOrderByBookIdDesc(int bookType);

    List<Book> findTop4ByBookTypeAndPriceLessThanEqualOrderByBookIdDesc(int bookType, int price);

    List<Book> findByTitleContaining(String query);

}