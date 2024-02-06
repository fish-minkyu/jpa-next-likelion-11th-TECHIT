package com.example.jpanext.shop.repo;

import com.example.jpanext.shop.entity.Item;
import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

  // 비관적 락 - Shared Lock
  @Lock(LockModeType.PESSIMISTIC_READ)
  @Query("SELECT i FROM Item i WHERE i.id = :id")
  Optional<Item> findItemForShare(
    @Param("id") Long id
  );

  // 비관적 락 - Exclusive Lock
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT i FROM Item i WHERE i.id = :id")
  Optional<Item> findItemForUpdate(
    @Param("id") Long id
  );

  @Override
  @NonNull
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Item> findById(@NonNull Long id);
}
