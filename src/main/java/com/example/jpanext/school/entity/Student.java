package com.example.jpanext.school.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor // @Entity 때문에 필요, 정확히 말하면 @AllArgsConstructor 때문에 기본 생성자가 사라져서 @NoArgsConstructor를 명시해준다.
@AllArgsConstructor // @Builder 때문에 필요
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  private String firstName;
  @Setter
  private String lastName;

  @ManyToMany
  // final과 ArrayList를 붙여주면 비어있는 상태라도 데이터를 넣어줄 수 있다.
  // (NullPointerException 방지)
  private final List<Lecture> attending = new ArrayList<>();
}
