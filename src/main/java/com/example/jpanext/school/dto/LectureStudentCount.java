package com.example.jpanext.school.dto;

import com.example.jpanext.school.entity.Lecture;

public interface LectureStudentCount {
  Lecture getLecture();

  Long getStudentCount();
}
