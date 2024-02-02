package com.example.jpanext.school.entity;

// Join Table을 직접 구현해보기
// : Join Table에 컬럼을 추가해보자

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attending_lectures")
public class AttendingLectures {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 복합키 관계를 굳이 설정하지 않아도 Join Table을 만들 수 있다.
  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "lecture_id")
  private Lecture lecture;

  private Integer midTermScore;
  private Integer finalScore;
}

// 단점은 조회하는 건 까다롭게 된다. (Lecture과 Student 둘 다 필요하기 때문)
// So, 조회할 수 있는 2가지 방법
// 1. Student와 Lecture 테이블에 @OneToMany로 설정하고 mappedBy를 설정해서 조회
// 2. AttendingLectures의 repository를 만들어 Query Method로 조회한다.
