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
//    List<Book> findByIsbn(String isbn); // isbn으로 검색 list로 할지 book으로 할지..
//
//    List<Book> findByBookType(int bookType); // 북타입으로 검색
//
//    List<Book> findByTitleContainingIgnoreCase(String keyword); // 제목에 특정 키워드가 포함된 책을 검색하는 메소드(대소문자 구분 없이)
//
//    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublisherContainingIgnoreCase(String title, String author, String publisher);
//    // 제목이나 저자명이나 출판사에 특정 키워드가 포함된 책을 검색하는 메소드
//    List<Book> findAllByOrderByTitle(); //제목순으로 검색

//    List<Book> findAll(); // 모든 책을 검색하는 메소드

//    long count();

}