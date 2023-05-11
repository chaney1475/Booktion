package com.project.Booktion.controller.auctionBook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/user/selling")
@RequiredArgsConstructor
@SessionAttributes("user")
public class SoldAuctionController {
}
