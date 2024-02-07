package com.example.jpanext.school;

import com.example.jpanext.school.dto.ILCountDto;
import com.example.jpanext.school.dto.ILCountProjection;
import com.example.jpanext.school.entity.AttendingLectures;
import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import com.example.jpanext.school.repo.AttendingLectureRepo;
import com.example.jpanext.school.repo.InstructorRepository;
import com.example.jpanext.school.repo.LectureRepository;
import com.example.jpanext.school.repo.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
  private final StudentRepository studentRepository;
  private final LectureRepository lectureRepository;
  private final AttendingLectureRepo attendingLectureRepo;
  private final InstructorRepository instructorRepository;

  @GetMapping("/pageable")
  public String pageable() {
    Page<Instructor> instructorPage
      = instructorRepository.findFetchPage(PageRequest.of(0, 5));

    return "done";
  }

  @GetMapping("/multi-bag")
  public String multiBag() {
    // Error, MultipleBagFetchException
    List<Instructor> instructors
      = instructorRepository.findWithStudentAndLecture();

    for (Instructor instructor: instructors) {
      log.info("{}", instructor.getAdvisingStudents().size());
      log.info("{}", instructor.getLectures().size());
    }


    return "done";
  }

  @GetMapping("/entity-graph")
  public String entityGraph() {
    List<Instructor> instructors = instructorRepository.findByEntityGraph();
    for (Instructor instructor: instructors) {
      log.info("{}", instructor.getAdvisingStudents().size());
    }

    return "done";
  }

  // Fetch-join
  // : 데이터를 한번에 가지고 오고 데이터를 추가로 로딩을 할 필요가 없다.
  @GetMapping("/fetch-join")
  public String fetchJoin() {
    List<Student> students = studentRepository.findAllFetchAdvisor();

    // Fetch Join으로 데이터를 한꺼번에 조회 (즉, DB에 한번 조회가 되었다.)
    for (Student student: students) {
      student.getAdvisor().getName();
    }

    List<Instructor> instructors = instructorRepository.findAllFetchStudents();

    for (Instructor instructor: instructors) {
      instructor.getAdvisingStudents().size();
    }


    return "done";
  }

  // Join해서 가져오기
  @GetMapping("/join")
  public String join() {
    log.info("{}", studentRepository.findAllJoin().size());
    log.info("{}", studentRepository.findAllLeftJoin().size());
    log.info("{}", studentRepository.findAllRightJoin().size());
    log.info("{}", studentRepository.findByAdvisorName("Plato Best"));

    // jPQL join을 했지만 N + 1로 조회가 됨
//    for (Student student: studentRepository.findAllJoin()) {
//      student.getAdvisor().getName();
//    }

    return "done";
  }

  // N + 1 강의 코드
  @GetMapping("/fetch-type")
    public String fetchType() {
//        List<Instructor> instructors = instructorRepository.findAll();
//        for (Instructor instructor: instructors) {
//            // PersistentBag
//            log.info("{}", instructor.getAdvisingStudents().getClass());
//        }
      List<Student> students = studentRepository.findAll();
    for (Student student: students) {
      if (student.getAdvisor() != null) {
        log.info("{}", student.getAdvisor().getClass());
        log.info("{}", student.getAdvisor().getId());
      }
    }

      // SELECT t FROM T t;
      instructorRepository.findAll();
      studentRepository.findAll();

      return "done";
  }

  @GetMapping("test-agg")
  public String testAggregate() {
    // List는 테이블 전체 행들을 나타내고
    // Object[]는 행들을 나타내고 있다.
    List<Object[]> results =
      instructorRepository.selectILCountObject();

    // List<Object[]>
    for (Object[] row: results) {
      Instructor instructor = (Instructor) row[0];
      // log.info(String.valueOf(row[1].getClass())); // Long
      Long count = (Long) row[1];

      log.info("{}: {}", instructor.getName(), count);
    }

    // 반환타입을 Dto로 바꿔주기
    List<ILCountDto> resultDtos =
      instructorRepository.selectILCountDto();

    for (ILCountDto dto : resultDtos) {
      log.info("{}: {}",
        dto.getInstructor().getName(),
        dto.getCount());
    }

    // 반환타입을 Projection으로 바꿔주기
    List<ILCountProjection> resultProjs =
      instructorRepository.selectILCountProj();
    for (ILCountProjection projection: resultProjs) {
      log.info("{}: {}",
        projection.getInstructor(),
        projection.getLectureCount());
    }

    lectureRepository.selectWithStudentCount1()
      .forEach(objects ->
        log.info("{}: {}", objects));

    lectureRepository.selectWithStudentCount2()
      .forEach(projection ->
        log.info("{}: {}",
          projection.getLecture().getName(),
          projection.getStudentCount()
          ));

    return "done";
  }

  @GetMapping("/test-query")
  public String testQuery() {
    log.info("JPQL Simple");
    lectureRepository.findLecturesBeforeLunch().forEach(lecture ->
      log.info("{}: {}", lecture.getName(), lecture.getStartTime()));

    lectureRepository.findLecturesBeforeLunchNative().forEach(lecture ->
      log.info("{}: {}", lecture.getName(), lecture.getStartTime()));


    log.info("============= indexed parameters");
    lectureRepository.findLecturesByTime(10, 13).forEach(lecture ->
      log.info("{}: {} -> {}",
        lecture.getName(),
        lecture.getStartTime(),
        lecture.getEndTime()
        ));

    lectureRepository.findLectureByTimeNative(10, 15).forEach(lecture ->
      log.info("{}: {} -> {}",
        lecture.getName(),
        lecture.getStartTime(),
        lecture.getEndTime()
        ));

    log.info("================ named parameters");
    lectureRepository.findLecturesByTimeNamed(10, 13).forEach(lecture ->
      log.info("{}: {} -> {}",
        lecture.getName(),
        lecture.getStartTime(),
        lecture.getEndTime()
      ));

    lectureRepository.findByDayIn(Set.of("mon", "tue")).forEach(lecture ->
      log.info("{}: {}",
        lecture.getName(),
        lecture.getStartTime(),
        lecture.getEndTime()
      ));

    Page<Lecture> lecturePage
      = lectureRepository.findAll(PageRequest.of(0, 10));

    // Pageable
    lecturePage = lectureRepository.findLecturesBeforeLunch(
      PageRequest.of(0, 4));
    lecturePage.stream().forEach(lecture ->
      log.info("{}: {}", lecture.getName(), lecture.getStartTime()));

    // Pageable - nativeQuery = true
    lectureRepository.findLecturesBeforeLunchNative(
      PageRequest.of(0, 4)).forEach(lecture ->
      log.info("{}: {}", lecture.getId(), lecture.getStartTime())
    );

    // id 역순 정렬
    lectureRepository.findLecturesBeforeLunch(
    Sort.by(Sort.Direction.DESC, "id")).forEach(lecture ->
      log.info("{}: {}", lecture.getId(), lecture.getStartTime()));

    return "done";
  }

  @Transactional // 데이터 수정에 반드시 필요, 컨트롤러 위에도 넣을 수 있다.
  @GetMapping("/test-modifying")
  public String modifying() {
    log.info("modifying");
    // 강의 시간이 3시간 초과인 강의 리스트 출력
    lectureRepository.toLongLectures().forEach(lecture ->
      log.info("{}: {}",
        lecture.getName(),
        lecture.getEndTime() - lecture.getStartTime()));

    // Update
    lectureRepository.setLectureMaxHour3();
    log.info("lectures over 3 hours: {}",
      lectureRepository.toLongLectures().size());

    // 지도교수가 없는 학생들 조회
    studentRepository.noAdvisorStudent().forEach(student ->
      log.info("{}, {}",student.getId(), student.getName()));

    // 지도교수가 없는 학생들을에게 지도교수 배정
    Instructor instructor = instructorRepository.findById(1L).get();
    log.info("rows affected: {}",
      studentRepository.setAdvisorForStudent(instructor));

    // 잘 배정이 되었는지 확인하기 위해 재조회
    studentRepository.noAdvisorStudent().forEach(student ->
      log.info("{}, {}",student.getId(), student.getName()));

    return "done";
  }

  @GetMapping("/many-to-many")
  public String test() {
    Student alex = Student.builder()
      .name("alex")
      .build();

    // JPA에서 save로 반환하면 alex가 어떻게 저장이 되었는지 모르기 때문에
    // 돌려받은 반환값을 가지고 나머지를 진행해줘야 한다.
    alex = studentRepository.save(alex);

    Student brad = Student.builder()
      .name("brad")
      .build();

    Lecture jpa = Lecture.builder()
      .name("jpa")
      .startTime(9)
      .endTime(16)
      .build();

    jpa = lectureRepository.save(jpa);

    Lecture spring = Lecture.builder()
      .name("Spring boot")
      .startTime(9)
      .endTime(16)
      .build();

    spring = lectureRepository.save(spring);

    alex.getAttending().add(jpa);
    alex.getAttending().add(spring);
    brad.getAttending().add(spring);
    studentRepository.save(alex); // alex의 상태가 변했으니까 최신화
    studentRepository.save(brad);
    lectureRepository.save(spring);

    return "done";
  }

  @GetMapping("many-to-many-get")
  public String manyToManyGet() {
    Student alex = studentRepository.findById(1L).get(); // alex라고 예상

    for (Lecture lecture: alex.getAttending()) {
      log.info("{} listens {}", alex.getName(), lecture.getName());
    }
    Lecture spring = lectureRepository.findById(2L).get();

    for (Student student : spring.getStudents()) {
      log.info("{} listen {}", student.getName(), spring.getName());
    }

    for (AttendingLectures attendingLecture: alex.getAttendingLectures()) {
      attendingLecture.setMidTermScore(80);
      attendingLecture.setFinalScore(80);
      attendingLectureRepo.save(attendingLecture);
    }

    return "done";
  }

  @GetMapping("/one-to-many-temp")
  public String oneToManyTemp() {
    // 강사를 만들고
    Instructor instructor = Instructor.builder()
      .name("Isac Newton")
      .build();
    // 강사를 저장
    instructor = instructorRepository.save(instructor);

    // 여러 학생을 만들고
    Student alex = Student.builder()
      .name("Alex")
      .advisor(instructor)
      .build();

    Student brad = Student.builder()
      .name("brad")
      .advisor(instructor)
      .build();

    // 학생을 저장한다.
    studentRepository.save(alex);
    studentRepository.save(brad);

    return "done";
  }

  // CascadeType.PERSIST일 때, 전부 저장됨
  @GetMapping("/cascade-persist")
  public String cascadePersist() {
//    @OneToMany(mappedBy = "advisor", cascade = CascadeType.PERSIST) // PERSIST: 저장하다
//    private final List<Student> advisingStudents = new ArrayList<>();

    // 강사를 만들고,
    Instructor instructor = Instructor.builder()
      .name("Isac Newton")
      .build();
    // 여러 학생을 만들고,
    Student alex = Student.builder()
      .name("Alex")
      .advisor(instructor)
      .build();

    Student brad = Student.builder()
      .name("Brad")
      .advisor(instructor)
      .build();
    // 강사의 지도학생으로 등록한다.
    instructor.getAdvisingStudents().add(alex);
    instructor.getAdvisingStudents().add(brad);
//    instructor.getAdvisingStudents().addAll(List.of(alex, brad));
    instructorRepository.save(instructor);

    return "done";
  }

  // CascadeType.REMOVE 일 때, 전체 삭제 됨
  @GetMapping("/cascade-remove")
  public String cascadeRemove() {
//    @OneToMany(mappedBy = "advisor", cascade = CascadeType.REMOVE)
//    private final List<Student> advisingStudents = new ArrayList<>();

    instructorRepository.deleteById(1L);
    return "done";
  }
}
// 제약사항은 DB에서 무결성을 지키기 위해서 만들어진 것이다.
// 연관된 데이터가 삭제가 될 때, 제약사항이 어기게 되는데
// A랑 연관이 되어있는 B가 먼저 삭제되어야 하고 그 다음 A가 삭제되어야 한다.
