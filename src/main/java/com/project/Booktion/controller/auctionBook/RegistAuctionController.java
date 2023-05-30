package com.project.Booktion.controller.auctionBook;


import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.Book;
import com.project.Booktion.service.AuctionBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction")
@RequiredArgsConstructor
public class RegistAuctionController {

    private final AuctionBookService auctionS;
    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("bookType",3);
        return "searchApi";
    }

    @PostMapping("/selected")
    public String handleSelectedBook(@ModelAttribute Book selectedBook, Model model, @RequestParam String deliveryCompany, @RequestParam int price) {
        // 선택된 책과 배송사 정보를 처리하는 로직을 작성합니다.
        // 선택된 책을 model에 추가하여 뷰에서 사용할 수 있게 합니다.
        selectedBook.setBookType(3);

        model.addAttribute("selectedBook", selectedBook);
        AuctionBook auctionBook = new AuctionBook();
        auctionBook.setShippingCompany(deliveryCompany);
        auctionBook.setBook(selectedBook);
        auctionBook.setStatus(0);
        auctionBook.setStartPrice(price);

        // 다음 뷰로 이동합니다. 이 뷰에서는 선택된 책의 상세 정보를 표시할 수 있습니다.
        return "selectedBookView";
    }

    @PostMapping("/registForm")
    public String registForm(@ModelAttribute AuctionBook book){
        // 검증 코드 생략
        AuctionBook auctionBook = auctionS.addNewBook(book);
        long bookId = auctionBook.getAuctionBookId();
        return "redirect:/auction/book/{bookId}";
    }
}
