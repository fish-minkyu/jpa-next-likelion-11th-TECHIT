package com.example.jpanext.article.entity;


import com.example.jpanext.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter // 상속 받은 컬럼들은 Setter를 할 수 없다.
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Article
  // BaseEntity을 상속받음으로써 BaseEntity의 속성이 Article에 들어온다.
  extends BaseEntity {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
  private String title;
  private String content;
  private String writer;

  @OneToMany(mappedBy = "article")
  private final List<Comment> comments = new ArrayList<>();
}
