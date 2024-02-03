package com.example.jpanext.school.dto;

import com.example.jpanext.school.entity.Instructor;

public interface ILCountProjection {
  /*
  private Instructor instructor;
  private Long lectureCount
  */

  // 인터페이스는 속성을 가질 수 없으므로 Getter를 만들어준다.
  Instructor getInstructor();

  Long getLectureCount();
}
