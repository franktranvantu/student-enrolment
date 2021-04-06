package studentenrolment;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleStudentEnrolment implements StudentEnrolmentManager {

  private static final Scanner SCANNER = new Scanner(System.in);
  private static final List<StudentEnrolment> STUDENT_ENROLMENTS = new ArrayList<>();
  private static final StudentDao STUDENT_DAO = new StudentDao();
  private static final CourseDao COURSE_DAO = new CourseDao();

  @Override
  public void add(StudentEnrolment studentEnrolment) {
    STUDENT_ENROLMENTS.add(studentEnrolment);
  }

  @Override
  public void update(int enrolmentId, StudentEnrolment studentEnrolment) {
    StudentEnrolment existedEnrolment = getOne(enrolmentId);
    if (existedEnrolment != null) {
      int index = STUDENT_ENROLMENTS.indexOf(existedEnrolment);
      StudentEnrolment enrolment = new StudentEnrolment(studentEnrolment);
      STUDENT_ENROLMENTS.set(index, enrolment);
    }
  }

  @Override
  public void delete(int enrolmentId) {
    StudentEnrolment existedEnrolment = getOne(enrolmentId);
    if (existedEnrolment != null) {
      STUDENT_ENROLMENTS.remove(existedEnrolment);
    }
  }

  @Override
  public StudentEnrolment getOne(int enrolmentId) {
    return STUDENT_ENROLMENTS.stream()
        .filter(enrolment -> enrolment.getId() == enrolmentId)
        .parallel()
        .findAny()
        .orElse(null);
  }

  @Override
  public StudentEnrolment getOneByStudentAndCourseAndSemester(int studentId, int courseId, String semester) {
    return STUDENT_ENROLMENTS.stream()
            .filter(studentEnrolment ->
                    studentEnrolment.getStudent().getId() == studentId
                    && studentEnrolment.getSemester().equalsIgnoreCase(semester)
                    && studentEnrolment.getCourse().getId() == courseId)
            .parallel()
            .findAny()
            .orElse(null);
  }

  @Override
  public List<StudentEnrolment> getAll() {
    return STUDENT_ENROLMENTS;
  }

  @Override
  public void printAllCoursesForStudentInSemester() {
    List<Student> students = STUDENT_DAO.getAll();

    students.stream().forEach(student -> {
      Map<String, List<Course>> semesters = STUDENT_ENROLMENTS.stream()
              .filter(studentEnrolment -> studentEnrolment.getStudent().getId() == student.getId())
              .collect(Collectors.groupingBy(
                      StudentEnrolment::getSemester,
                      Collectors.mapping(StudentEnrolment::getCourse, Collectors.toList()))
              );
      System.out.println("============================================================");
      System.out.printf("Student Id: %s, Student Name: %s, Birthday: %s%n", student.getId(), student.getName(), student.getBirthday());
      semesters.entrySet().stream().forEach(semester -> {
        String semesterName = semester.getKey();
        System.out.printf("Semester: %s%n", semesterName);
        COURSE_DAO.print(semester.getValue());
      });
    });
  }

  @Override
  public void printAllCoursesForSpecificStudentInSpecificSemester(int studentId, String semester) {
    Student student = STUDENT_DAO.getOne(studentId);
    List<Course> courses = STUDENT_ENROLMENTS.stream()
            .filter(studentEnrolment -> studentEnrolment.getStudent().getId() == studentId && studentEnrolment.getSemester().equalsIgnoreCase(semester))
            .map(StudentEnrolment::getCourse)
            .collect(Collectors.toList());
    System.out.println("============================================================");
    System.out.printf("Student Id: %s, Student Name: %s, Birthday: %s%n", student.getId(), student.getName(), student.getBirthday());
    System.out.printf("Semester: %s%n", semester);
    COURSE_DAO.print(courses);

    if (!courses.isEmpty()) {
      System.out.println("Do you want to export to CSV file? Yes(Y) / No(N)");
      String choose = SCANNER.nextLine();
      if ("Y".equalsIgnoreCase(choose)) {
        try {
          String fileName = "all-courses-for-1-student-in-1-semester.csv";
          String[] headers = {"Course Id", "Course Name", "Credit Number"};
          FileWriter out = new FileWriter(fileName);
          try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
            for (Course course : courses) {
              printer.printRecord(course.getId(), course.getName(), course.getCreditNumber());
            }
          }
          System.out.printf("Exported report. File name is %s%n", fileName);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void printAllStudentsOfCourseInSemester() {
    List<Course> courses = COURSE_DAO.getAll();

    courses.stream().forEach(course -> {
      Map<String, List<Student>> semesters = STUDENT_ENROLMENTS.stream()
              .filter(studentEnrolment -> studentEnrolment.getCourse().getId() == course.getId())
              .collect(Collectors.groupingBy(
                      StudentEnrolment::getSemester,
                      Collectors.mapping(StudentEnrolment::getStudent, Collectors.toList()))
              );
      System.out.println("============================================================");
      System.out.printf("Course Id: %s, Course Name: %s, Credit Number: %s%n", course.getId(), course.getName(), course.getCreditNumber());
      semesters.entrySet().stream().forEach(semester -> {
        String semesterName = semester.getKey();
        System.out.printf("Semester: %s%n", semesterName);
        STUDENT_DAO.print(semester.getValue());
      });
    });
  }

  @Override
  public void printAllStudentsOfSpecificCourseInSpecificSemester(int courseId, String semester) {
    Course course = COURSE_DAO.getOne(courseId);
    List<Student> students = STUDENT_ENROLMENTS.stream()
            .filter(studentEnrolment -> studentEnrolment.getCourse().getId() == courseId && studentEnrolment.getSemester().equalsIgnoreCase(semester))
            .map(StudentEnrolment::getStudent)
            .collect(Collectors.toList());
    System.out.println("============================================================");
    System.out.printf("Course Id: %s, Course Name: %s, Credit Number: %s%n", course.getId(), course.getName(), course.getCreditNumber());
    System.out.printf("Semester: %s%n", semester);
    STUDENT_DAO.print(students);

    if (!students.isEmpty()) {
      System.out.println("Do you want to export to CSV file? Yes(Y) / No(N)");
      String choose = SCANNER.nextLine();
      if ("Y".equalsIgnoreCase(choose)) {
        try {
          String fileName = "all-students-for-1-course-in-1-semester.csv";
          String[] headers = {"StudentId Id", "Student Name", "Birthday"};
          FileWriter out = new FileWriter(fileName);
          try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
            for (Student student : students) {
              printer.printRecord(student.getId(), student.getName(), student.getBirthday());
            }
          }
          System.out.printf("Exported report. File name is %s%n", fileName);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void printAllCoursesOfferedInSemester() {

  }

  @Override
  public void printAllCoursesOfferedInSpecificSemester(String semester) {
    List<Course> courses = STUDENT_ENROLMENTS.stream()
            .filter(studentEnrolment -> studentEnrolment.getSemester().equalsIgnoreCase(semester))
            .map(StudentEnrolment::getCourse)
            .collect(Collectors.toList());
    System.out.println("============================================================");
    System.out.printf("Semester: %s%n", semester);
    COURSE_DAO.print(courses);

    if (!courses.isEmpty()) {
      System.out.println("Do you want to export to CSV file? Yes(Y) / No(N)");
      String choose = SCANNER.nextLine();
      if ("Y".equalsIgnoreCase(choose)) {
        try {
          String fileName = "all-courses-offered-in-1-semester.csv";
          String[] headers = {"Course Id", "Course Name", "Credit Number"};
          FileWriter out = new FileWriter(fileName);
          try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
            for (Course course : courses) {
              printer.printRecord(course.getId(), course.getName(), course.getCreditNumber());
            }
          }
          System.out.printf("Exported report. File name is %s%n", fileName);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
