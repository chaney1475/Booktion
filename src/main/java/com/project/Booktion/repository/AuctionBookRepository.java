package com.project.Booktion.repository;

import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.AuctionBookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuctionBookRepository extends JpaRepository<AuctionBook, Long>{

    // userId로 acutionBook 검색
    @Query("SELECT ab FROM AuctionBook ab WHERE ab.book.user.userId = :userId AND ab.status = 0")
    List<AuctionBook> findByUserSelling(@Param("userId") String userId);
}
