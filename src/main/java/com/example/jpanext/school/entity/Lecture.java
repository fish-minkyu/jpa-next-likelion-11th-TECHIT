package com.example.jpanext.school.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  private String name;
  @Setter
  private String day;
  @Setter
  private Integer startTime;
  @Setter
  private Integer endTime;

  // mappedBy
  // 1. 상세설정은 mappedBy가 가르키는 속성에 따른다.
  // 2. mappedBy를 안해주게 되면 경유하는 테이블이 2개 생긴다.
  //  : mappedBy로 나타내는 컬럼이 있는 테이블이
  //    운영 목적 상에서 해당 테이블이 관계에 대해서 많이 저장할 것을 의미한다.
  // Ex. attending은 Student의 컬럼이므로 Student 테이블이 관계에 대해서 많이 저장할 것이다.
  @ManyToMany(mappedBy = "attending")
  private final List<Student> students = new ArrayList<>();

    // 복수 관계 설정
//  @ManyToMany(mappedBy = "completed")
//  private final List<Student> completedStudents = new ArrayList<>();
}
