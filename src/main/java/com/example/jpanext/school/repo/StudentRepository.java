package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findAllByAdvisorId(Long id);

  List<Student> findAllByAdvisor(Instructor instructor);

  // 지도교수가 null인 학생을 반환
  @Query("SELECT s FROM Student s " +
    "WHERE s.advisor Is null ")
  List<Student> noAdvisorStudent();

  // 지도교수가 null인 학생에게 instructor 넣기
  @Modifying
  @Query("UPDATE Student s " +
    "SET s.advisor = :advisor " +
    "WHERE s.advisor Is null ")
  Integer setAdvisorForStudent(
    @Param("advisor") Instructor instructor
  );
}
