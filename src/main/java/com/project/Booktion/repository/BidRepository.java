package com.project.Booktion.repository;

import com.project.Booktion.model.Bid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {

    List<Bid> findByAuctionBookAuctionBookId(long auctionBookId);
    @Query(value = "SELECT b FROM Bid b WHERE b.auctionBook.auctionBookId = :auctionBookId ORDER BY b.price DESC")
    List<Bid> gethighestBid(@Param("auctionBookId") Long auctionBookId);

    //Bid findFirstByAuctionBookAuctionBookIdOrderByPriceDesc(Long auctionBookId);

}
