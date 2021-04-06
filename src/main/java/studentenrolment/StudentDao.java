package studentenrolment;

import dnl.utils.text.table.TextTable;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class StudentDao {

  private static final List<Student> STUDENTS = Arrays.asList(
          new Student(1, "Student 1", LocalDate.of(2000, Month.JANUARY, 01)),
          new Student(2, "Student 2", LocalDate.of(2000, Month.FEBRUARY, 02)),
          new Student(3, "Student 3", LocalDate.of(2000, Month.MARCH, 03)),
          new Student(4, "Student 4", LocalDate.of(2000, Month.APRIL, 04)),
          new Student(5, "Student 5", LocalDate.of(2000, Month.MAY, 05))
  );

  public List<Student> getAll() {
    return STUDENTS;
  }

  public Student getOne(int studentId) {
    return STUDENTS.stream()
            .filter(student -> student.getId() == studentId)
            .parallel()
            .findAny()
            .orElse(null);
  }

  public void print(List<Student> students) {
    System.out.println("========== List of students ==========");
    String[] headers = new String[] {"Student Id", "Student Name", "Birthday"};
    Object[][] data = new Object[students.size()][headers.length];
    for (int i = 0; i < students.size(); i++) {
      Student student = students.get(i);
      data[i][0] = student.getId();
      data[i][1] = student.getName();
      data[i][2] = student.getBirthday();
    }
    TextTable tt = new TextTable(headers, data);
    tt.printTable();
  }
}
