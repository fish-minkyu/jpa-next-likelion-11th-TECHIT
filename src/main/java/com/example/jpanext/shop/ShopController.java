package com.example.jpanext.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
  private final ShopService shopService;
  private final ParentService parentService;

  @GetMapping("/create-order")
  public String createOrder() {
    shopService.createOrder();
    return "done";
  }

  // childService.supports()일 때
  // @Transactional이 있으면 아무것도 생성되지 않고 child throws 발생
  // 반대로 없으면 insert가 3번 일어난 후 child throws 발생

  // childService.mandatory()일 때,
  // @Transaction이 있으면 아무것도 생성되지 않고  child throws 발생
  // 없어도 아무것도 생성되지 않고  child throws 발생
  // (mandatory() 메서드를 호출한 propagation메서드가 Transaction이 아니므로)
  @Transactional
  @GetMapping("/propagation")
  public void propagation() {
    parentService.none();
  }

}
