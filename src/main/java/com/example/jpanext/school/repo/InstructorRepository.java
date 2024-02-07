package com.example.jpanext.school.repo;

import com.example.jpanext.school.dto.ILCountDto;
import com.example.jpanext.school.dto.ILCountProjection;
import com.example.jpanext.school.entity.Instructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository  extends JpaRepository<Instructor, Long> {

  @EntityGraph(
    attributePaths = {"advisingStudents", "lectures"},
    type = EntityGraph.EntityGraphType.FETCH
  )
  @Query("SELECT DISTINCT i FROM Instructor i")
  List<Instructor> findWithStudentAndLecture();

  // 데이터를 한번에 가지고 오고 데이터를 추가로 로딩을 할 필요가 없다.
  // 또한, 지정된 속성을 EAGER로 조회
  @EntityGraph(
    attributePaths = {"advisingStudents"},
    type = EntityGraph.EntityGraphType.FETCH
  )
  @Query("SELECT DISTINCT i FROM Instructor i")
  List<Instructor> findByEntityGraph();


  // 데이터를 한번에 가지고 오고 데이터를 추가로 로딩을 할 필요가 없다.
  @Query("SELECT DISTINCT i FROM Instructor i LEFT JOIN FETCH i.advisingStudents")
  List<Instructor> findAllFetchStudents();

  // 지도학생을 데리고 있지 않은 강사를 삭제
  @Modifying
  @Query("DELETE FROM Instructor i " +
    // size()는 JPQL이 제공하는 기능
    "WHERE size(i.advisingStudents) = 0 ")
  Integer sackInstructorNotAdvising();

  // 강의를 하고 있지 않은 강사를 삭제
  @Modifying
  @Query("DELETE FROM Instructor i " +
    "WHERE i.id NOT  IN " +
    "(SELECT DISTINCT l.instructor.id FROM Lecture l)")
  Integer sackInstructorsNotTeaching();

  // Instructor가 가지고 있는 Lecture을 세보자.
  // 아무 준비 없이 집계함수를 쓴다면 List<Object[]>으로 반환한다.
  // 당연 아주 많은 오류를 불러일으킨다.
  // (Object가 서버를 죽이는 원인이 된다.)
  @Query("SELECT l.instructor, COUNT(*) FROM Lecture l " +
    "GROUP BY l.instructor")
  List<Object[]> selectILCountObject();

  // 반환타입을 Dto로 바꿔주기
  // Dto가 import가 안되는 가능성이 있기 때문에 Dto를 명확히 표시해줘야 한다.
  // Ex. com.example.jpanext.school.dto.ILCountDto()
  @Query("SELECT new com.example.jpanext.school.dto.ILCountDto(l.instructor, COUNT(*)) " +
    "FROM Lecture l " +
    "GROUP BY l.instructor")
  List<ILCountDto> selectILCountDto();

  // Projection
  // 반환타입을 Projection으로 바꿔주기
  @Query("SELECT l.instructor AS instructor, COUNT(*) AS lectureCount " +
    "FROM Lecture l " +
    "GROUP BY l.instructor")
  List<ILCountProjection> selectILCountProj();
}
