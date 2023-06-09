package com.project.Booktion.service;

import com.project.Booktion.model.Bid;
import com.project.Booktion.model.TempOrder;
import com.project.Booktion.repository.BidRepository;
import com.project.Booktion.repository.TempOrderRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BiddingService {

    private final BidRepository bidRepository;
    private final TempOrderRepository tempOrderRepository;

    public TempOrder createTempOrder(Long auctionBookId) {
        //AuctionBookId로 Bid 테이블을 검색하여 가장 높은 price를 가진 Bid를 가져옴
        Bid highestBid = bidRepository.gethighestBid(auctionBookId).get(0);

        if (highestBid != null) {
            // TempOrder 생성
            TempOrder tempOrder = new TempOrder();
            tempOrder.setAuctionBookId(auctionBookId);
            tempOrder.setBid(highestBid);
            tempOrder.setUserId(highestBid.getBidderId()); //
            tempOrder.setBookInfo(highestBid.getAuctionBook().getBook());

            // TempOrder 저장
            return (TempOrder) tempOrderRepository.save(tempOrder);
        }
        else return null;
    }

    public TempOrder findTempOrder(Long tempOrderId) {
        TempOrder tempOrder = (TempOrder) tempOrderRepository.findById(tempOrderId).orElse(null);
        if(tempOrder != null){
            return tempOrder;
        }
        return null;
    }
    public List<Bid> getBids (long bookId){
        return bidRepository.findByAuctionBookAuctionBookId(bookId);
    }
    public Bid addBid(Bid bid){
        return bidRepository.save(bid);
    }
    public List<Bid> myBids(String userId){
        return bidRepository.findByBidderId(userId);
    }
}
