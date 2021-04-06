package studentenrolment;

import java.util.Objects;

public class StudentEnrolment {

  private int id;
  private Student student;
  private Course course;
  private String semester;

  public StudentEnrolment(int id, Student student, Course course, String semester) {
    this.id = id;
    this.student = student;
    this.course = course;
    this.semester = semester;
  }

  public StudentEnrolment(Student student, Course course, String semester) {
    this.id++;
    this.student = student;
    this.course = course;
    this.semester = semester;
  }

  public StudentEnrolment(StudentEnrolment studentEnrolment) {
    this(studentEnrolment.getId(), studentEnrolment.getStudent(), studentEnrolment.getCourse(), studentEnrolment.getSemester());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudentEnrolment enrolment = (StudentEnrolment) o;
    return id == enrolment.id && Objects.equals(student, enrolment.student) && Objects.equals(course, enrolment.course) && Objects.equals(semester, enrolment.semester);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, student, course, semester);
  }

  @Override
  public String toString() {
    return "StudentEnrolment{" +
        "id=" + id +
        ", student=" + student +
        ", course=" + course +
        ", semester='" + semester + '\'' +
        '}';
  }
}
