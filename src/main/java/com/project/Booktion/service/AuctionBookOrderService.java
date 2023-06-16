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
        AuctionBook book = auctionOrderR.findByAuctionBookAuctionBookId(bookId);
        if(book != null){
            return true;
        }
        return false;
    }
}
