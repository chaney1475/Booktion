package com.project.Booktion.service;

import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.AuctionBookOrder;
import com.project.Booktion.repository.AuctionBookOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AuctionBookOrderService {

    private final AuctionBookOrderRepository auctionOrderR;
    public List<AuctionBookOrder> getUserOrder(String userId){
        return auctionOrderR.findByUserIdSold(userId);
    }
    public boolean findAuctionBook(long bookId){
        AuctionBookOrder order = auctionOrderR.findByAuctionBookAuctionBookId(bookId);
        if(order != null){
            return true;
        }
        return false;
    }
}
