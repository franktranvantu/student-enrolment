package studentenrolment;

import java.util.Objects;

public class Course {

  private int id;
  private String name;
  private String creditNumber;

  public Course(int id, String name, String creditNumber) {
    this.id = id;
    this.name = name;
    this.creditNumber = creditNumber;
  }

  public Course(Course course) {
    this(course.getId(), course.getName(), course.getCreditNumber());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreditNumber() {
    return creditNumber;
  }

  public void setCreditNumber(String creditNumber) {
    this.creditNumber = creditNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return id == course.id && Objects.equals(name, course.name) && Objects.equals(creditNumber, course.creditNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, creditNumber);
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", creditNumber='" + creditNumber + '\'' +
        '}';
  }
}
