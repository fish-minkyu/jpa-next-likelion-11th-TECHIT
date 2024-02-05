package com.example.jpanext.shop;

import com.example.jpanext.shop.entity.Customer;
import com.example.jpanext.shop.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentService {
  private final CustomerRepository customerRepository;
  private final ChildService childService;

  public void none() {
    customerRepository.save(Customer.builder()
        .name("Parent None").build());

    try {
      childService.supports();
//      childService.mandatory();
    } catch (Exception e) {
      log.warn(e.getMessage());
    }

    customerRepository.save(Customer.builder()
        .name("Parent None2").build());
    throw new RuntimeException("parent throw");
  }
}
