package com.project.Booktion.repository;

import com.project.Booktion.model.AuctionBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuctionBookRepository extends JpaRepository<AuctionBook, Long>{

    // userId로 acutionBook 검색
    @Query(value = "SELECT ab FROM AuctionBook ab JOIN ab.book b WHERE b.user.userId = :sellerId", nativeQuery = true)
    List<AuctionBook> findBySellerId(String sellerId);
}
