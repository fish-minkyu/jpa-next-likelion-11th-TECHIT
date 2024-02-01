package com.example.jpanext.school;

import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import com.example.jpanext.school.repo.LectureRepository;
import com.example.jpanext.school.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
  private final StudentRepository studentRepository;
  private final LectureRepository lectureRepository;

  @GetMapping("/test")
  public String test() {
    Student alex = Student.builder()
      .firstName("alex")
      .lastName("rod")
      .build();

    // JPA에서 save로 반환하면 alex가 어떻게 저장이 되었는지 모르기 때문에
    // 돌려받은 반환값을 가지고 나머지를 진행해줘야 한다.
    alex = studentRepository.save(alex);

    Lecture jpa = Lecture.builder()
      .name("jpa")
      .startTime(9)
      .endTime(16)
      .build();

    jpa = lectureRepository.save(jpa);

    alex.getAttending().add(jpa);
    studentRepository.save(alex); // alex의 상태가 변했으니까 최신화

    return "done";
  }
}
