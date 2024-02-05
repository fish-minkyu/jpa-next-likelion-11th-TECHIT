package com.example.jpanext.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "customer_order") // SQL의 order by 때문에 오류를 일으켜서 수정했다.
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer totalPrice;
  private String address;

  @ManyToOne
  private Customer customer; // 외래키명은 속성명_id를 기본으로 한다.
  @ManyToOne
  private PaymentMethod payment;

  // 관계의 소유(= 관계의 주인)
  // : 관계를 설정하는데 있어서 대부분의 설정들을 관계의 주인이 설정한다.
  @ManyToMany
  @JoinTable(
    name = "order_item",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "item_id")
  )
  private final List<Item> items = new ArrayList<>();
}
