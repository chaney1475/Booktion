package com.project.Booktion.repository;

import com.project.Booktion.model.AuctionBookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuctionBookOrderRepository extends JpaRepository<AuctionBookOrder, Long> {

    //내가 팔고있는 auctionBook을 검색하려 AuctionBookOrder로 반환
    List<AuctionBookOrder> findByOrderUserUserId(String userId);
    @Query("SELECT abo FROM AuctionBookOrder abo JOIN abo.auctionBook.book b JOIN b.user u WHERE u.userId = :userId")
    List<AuctionBookOrder> findByUserIdSold(@Param("userId") String userId);
}