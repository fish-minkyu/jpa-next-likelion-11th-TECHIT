package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Lecture;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
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

  // SQL도 반환 타입을 Object에서 다른 것으로 바꿔줄 수 있다.
  @Query(
    value = "SELECT * FROM lecture " +
      "WHERE start_time = ?1 AND end_time = ?2",
    nativeQuery = true
  )
  List<Lecture> findLectureByTimeNative(
    Integer startTime,
    Integer endTime
  );

  /*
  SELECT * FROM lecture WHERE day in ('mon', 'tue', 'wed')
  */
  @Query("SELECT l FROM Lecture l WHERE l.day IN :days")
  List<Lecture> findByDayIn(
    @Param("days")Collection<String> days
    );

  // Pageable (1/18일자 교안 참고)
  // => Page를 받도록 정의할 수 있다.
  @Query("SELECT l FROM Lecture l WHERE l.startTime < 12")
  Page<Lecture> findLecturesBeforeLunch(Pageable pageable);

  // nativeQuery = true
  @Query(
    value = "SELECT * FROM lecture WHERE start_time < 12",
    countQuery = "SELECT COUNT(*) FROM  lecture WHERE start_time < 12", // 총 페이지가 몇개의 결과가 나오는지가 필요하다.
    nativeQuery = true
  )
  Page<Lecture> findLecturesBeforeLunchNative(Pageable pageable);

  // Sort
  @Query("SELECT l FROM Lecture l WHERE l.startTime < 12")
  List<Lecture> findLecturesBeforeLunch(Sort sort);

  // 직접 전달할 쿼리를 작성하기 때문에 JpaRepository<T, ID>의 T와 무관하게 사용가능하나..
  // 별로 좋은 권장되는 방식은 아니다.
  // 즉, 오류 없이 결과는 출력이 되나, 향후 유지보수에 최악이다.
//  List<Instructor> findInstructorInLectureRepository();

  @Query("SELECT l FROM Lecture l " +
    "WHERE l.endTime - l.startTime > 3")
  List<Lecture> toLongLectures();

  // UPDATE, DELETE, INSERT의 경우 Modifying이 들어가야 한다.
  @Modifying
  @Query("UPDATE Lecture l " +
    "SET l.endTime = l.startTime + 3 " +
    "WHERE l.endTime - l.startTime > 3")
  Integer setLectureMaxHour3();

  // INSERT는 JPQL로 할 수 없다..
  // INSERT할 일이 있다면 그냥 save()를 쓰는 걸 추천..
  @Modifying
  @Query(
    value = "INSERT INTO lecture(name, start_time, end_time, instructor_id, day) " +
      "VALUES (:name, :startTime, :endTime, :instructorId, :day)",
    nativeQuery = true
  )
  void insertLecture(
    @Param("name") String name,
    @Param("startTime") Integer startTime,
    @Param("endTime") Integer endTime,
    @Param("instructorId") Long instructorId,
    @Param("day") String day
  );
}

