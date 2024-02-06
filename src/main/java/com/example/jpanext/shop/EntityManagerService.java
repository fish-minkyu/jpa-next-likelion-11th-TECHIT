package com.example.jpanext.shop;

import com.example.jpanext.shop.entity.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityManagerService {
  // EntityManager
  // : 영속성 컨텍스트와 직접 소통하는 객체
  // => EntityManager란 걸 사용해서 JPA와 통신하게 되는데, Spring Data Jpa를 쓰면
  // JpaRepository같은 인터페이스를 제공 받아 사용하면 EntityManager를 직접적으로 다루지 않아도 활용할 수 있다.
  private final EntityManager entityManager;


  @Transactional
  public void save() {
    Item newItem = Item.builder()
      .name("new item")
      .build();

    // repository의 save가 persist을 좀 더 사용하기 쉽게 만들어준 것이다.
    entityManager.persist(newItem);
    entityManager.persist(Item.builder()
      .name("new item2")
      .build());
  }

  @Transactional
  public void find() {
    Item targetItem = entityManager.find(Item.class, 1L);
    log.info(targetItem.getName());
  }
}
