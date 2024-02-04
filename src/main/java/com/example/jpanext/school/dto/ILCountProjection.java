package com.example.jpanext.school.dto;

import com.example.jpanext.school.entity.Instructor;

public interface ILCountProjection {
  /*
  인터페이스의 구현체가 반환하는 데이터를 가지고 있다고 생각하고
  Getter 메소드를 만들어서 사용한다.
  private Instructor instructor;
  private Long lectureCount
  */

  // 인터페이스는 속성을 가질 수 없으므로 Getter를 만들어준다.
  Instructor getInstructor();

  Long getLectureCount();
}
