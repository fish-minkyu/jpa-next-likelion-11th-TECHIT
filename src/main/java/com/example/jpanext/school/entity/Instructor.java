package com.example.jpanext.school.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Setter
  private String name;

  // OneToMany의 데이터가 필요해지면, 한번에 많은 데이터를 가져오도록
  // 이 때, 기준은 여러 instructor를 기준으로 List<Student>를 검색한다.
  @BatchSize(size = 10)
  @OneToMany(mappedBy = "advisor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private final List<Student> advisingStudents = new ArrayList<>();

  // cascade
  // : 영속성 전이, 내가 영속회되었다는 것을 밑에 관계를 맺고 있는 Entity에게 전파할 것인가?
  // CascadeType.PERSIST
  // : 내가 저장이 될 때, 나한테 관계가 되어있는 애들도 다 저장을 시키겠다.
  // CascadeType.REMOVE
  // : 내가 삭제가 될 때, 나한테 연관이 되어있는 애들이 먼저 삭제 후 나를 삭제 시키겠다.
  // CascadeType.ALL
  // : CascadeType.PERSIST + CascadeType.REMOVE
  @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private final List<Lecture> lectures = new ArrayList<>();
}
