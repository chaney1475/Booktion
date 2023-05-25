package com.project.Booktion.repository;

import com.project.Booktion.model.AuctionBookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuctionBookOrderRepository extends JpaRepository<AuctionBookOrder, Long> {
    @Query("SELECT ao FROM AuctionBookOrder ao JOIN FETCH ao.order o JOIN FETCH ao.auctionBook ab JOIN FETCH ab.book b WHERE ab.status = 1 AND b.sellerId = :sellerId")
    List<AuctionBookOrder> getSoldAuctionBookOrders(@Param("sellerId") String sellerId);


}