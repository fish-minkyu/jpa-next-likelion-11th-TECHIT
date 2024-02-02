package com.example.jpanext.school;

import com.example.jpanext.school.entity.AttendingLectures;
import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import com.example.jpanext.school.repo.AttendingLectureRepo;
import com.example.jpanext.school.repo.InstructorRepository;
import com.example.jpanext.school.repo.LectureRepository;
import com.example.jpanext.school.repo.StudentRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
  private final StudentRepository studentRepository;
  private final LectureRepository lectureRepository;
  private final AttendingLectureRepo attendingLectureRepo;
  private final InstructorRepository instructorRepository;

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
