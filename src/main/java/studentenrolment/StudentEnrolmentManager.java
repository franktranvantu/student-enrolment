package studentenrolment;

import java.util.List;

public interface StudentEnrolmentManager {

  void add(StudentEnrolment studentEnrolment);

  void update(int enrolmentId, StudentEnrolment studentEnrolment);

  void delete(int enrolmentId);

  StudentEnrolment getOne(int enrolmentId);

  List<StudentEnrolment> getAll();

  void printAllCoursesForStudentInSemester();
}
