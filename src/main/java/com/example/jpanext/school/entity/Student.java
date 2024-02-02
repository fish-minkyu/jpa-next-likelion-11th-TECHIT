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
// 테이블의 이름을 설정하고 싶을 때 (그 외의 기능도 많음)
// @Table(name = "Student_table")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  // 컬럼의 이름을 설정하고 싶을 때 (그 외의 기능도 많음)
////  @Column(name = "last_name")
//  private String lastName;

  @Setter
  // 컬럼의 이름을 설정하고 싶을 때 (그 외의 기능도 많음)
  @Column(name = "name")
  private String name;
  @Setter
  private Integer age;
  @Setter
  private String phone;
  @Setter
  private String email;

  @Setter
  @ManyToOne
  @JoinColumn(name = "advisor_id") // 컬럼 이름이 똑같이 나와도 명시적으로 표현해주기 위해 사용
  private Instructor advisor;

  // way1. ManyToMany 설정
  @ManyToMany
  // Join Table의 모습을 정의하고 싶을 (관계의 주가 되는 컬럼이 JoinTable을 쓴다.)
  @JoinTable(
    name = "attending_lectures",
    // Join Table의 나를 가르키는 FK의 설정
    joinColumns = @JoinColumn(name = "student_id"),
    // Join Table의 관계를 맺는 상대방을 가르키는 FK의 설정
    inverseJoinColumns = @JoinColumn(name = "lecture_id")
  )
  // final과 ArrayList를 붙여주면 비어있는 상태라도 데이터를 넣어줄 수 있다.
  // (NullPointerException 방지)
  private final List<Lecture> attending = new ArrayList<>();

  // way2. Join Table, Attending Lectures 레코 가져오기 위해 설정
  @OneToMany(mappedBy = "student")
  private final List<AttendingLectures> attendingLectures = new ArrayList<>();

    // 복수 관계 설정
//  @ManyToMany
//  private final List<Lecture> completed = new ArrayList<>();
}
