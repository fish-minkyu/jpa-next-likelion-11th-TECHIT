package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
  // JPQL, Entity와 Field로 동작 (nativeQuery = false)
  @Query(value = "SELECT l FROM Lecture l WHERE l.startTime < 12")
  List<Lecture> findLecturesBeforeLunch();

  // SQL
  @Query(
    value = "SELECT * FROM lecture WHERE start_time < 12",
    // 그냥 SQL 사용 옵션
    nativeQuery = true
    // "SELECT start_time, end_time FROM lecture WHERE start_time < 12"
    // : lecture를 만들 때, 데이터가 부족해서 오류가 난다.
    // 내가 돌려받을 데이터가 충분히 그리고 오해없이 데이터를 받을 수 있다란 보장이 있을 때
    // nativeQuery를 사용할 수 있다.
  )
  List<Lecture> findLecturesBeforeLunchNative();

  // 동적 쿼리

  // indexed parameters
  // 숫자는 인자의 순서를 의미한다.
  // 1 - startTime, 2 - endTime
  @Query("SELECT l FROM Lecture l " +
    "WHERE l.startTime = ?1 AND l.endTime = ?2")
  List<Lecture> findLecturesByTime(
    Integer startTime,
    Integer endTime
  );

  // named parameters
  @Query("SELECT l FROM Lecture l " +
    "WHERE l.startTime = :start " +
    "AND l.endTime = :end")
  List<Lecture> findLecturesByTimeNamed(
    @Param("start") Integer startTime,
    @Param("end") Integer endTime
  );
}
