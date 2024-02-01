package com.example.jpanext.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 다른 Entity가 상속 받아서 내가 가진 속성 정보를 포함시키고 싶을 때
// 상속 받는 Entity 클래스임을 나타내는 Annotation
// (공통도니 속성을 상속하기 위한 용도)
@Getter
@MappedSuperclass
// 해당 Entity 변경이 될 때, 이 변경사항을 듣고 있겠다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity { // 나 자신은 인스턴스를 못만들도록 추상 클래스 만들기
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate // article Entity가 언제 생성이 되었는지
  private LocalDateTime createdAt;
  @LastModifiedDate // article Entity가 언제 마지막 수정이 되었는지
  private LocalDateTime updatedAt;
}
