package studentenrolment;

import java.time.LocalDate;
import java.util.Objects;

public class Student {

  private int id;
  private String name;
  private LocalDate birthday;

  public Student(int id, String name, LocalDate birthday) {
    this.id = id;
    this.name = name;
    this.birthday = birthday;
  }

  public Student(Student student) {
    this(student.getId(), student.getName(), student.getBirthday());
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

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return id == student.id && Objects.equals(name, student.name) && Objects.equals(birthday, student.birthday);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, birthday);
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", birthday=" + birthday +
        '}';
  }
}
