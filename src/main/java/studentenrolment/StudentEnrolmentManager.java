package studentenrolment;

import java.util.List;

public interface StudentEnrolmentManager {

  void add(StudentEnrolment studentEnrolment);

  void update(int enrolmentId, StudentEnrolment studentEnrolment);

  void delete(int enrolmentId);

  void deleteByCourseId(int courseId);

  StudentEnrolment getOne(int enrolmentId);

  StudentEnrolment getOneByStudentAndCourseAndSemester(int studentId, int courseId, String semester);

  List<StudentEnrolment> getAll();

  void printAllCoursesForStudentInSemester();

  void printAllCoursesForSpecificStudentInSpecificSemester(int studentId, String semester);

  void printAllStudentsOfCourseInSemester();

  void printAllStudentsOfSpecificCourseInSpecificSemester(int courseId, String semester);

  void printAllCoursesOfferedInSemester();

  void printAllCoursesOfferedInSpecificSemester(String semester);
}
