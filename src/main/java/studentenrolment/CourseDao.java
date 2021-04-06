package studentenrolment;

import dnl.utils.text.table.TextTable;

import java.util.Arrays;
import java.util.List;

public class CourseDao {

  private static final List<Course> COURSES = Arrays.asList(
          new Course(1, "Course 1", "1111-1111-1111-1111"),
          new Course(2, "Course 2", "2222-2222-2222-2222"),
          new Course(3, "Course 3", "3333-3333-3333-3333"),
          new Course(4, "Course 4", "4444-4444-4444-4444"),
          new Course(5, "Course 5", "5555-5555-5555-5555")
  );

  public List<Course> getAll() {
    return COURSES;
  }

  public Course getOne(int courseId) {
    return COURSES.stream()
            .filter(course -> course.getId() == courseId)
            .parallel()
            .findAny()
            .orElse(null);
  }

  public void print(List<Course> courses) {
    System.out.println("========== List of courses ==========");
    String[] headers = new String[] {"Course Id", "Course Name", "Credit Number"};
    Object[][] data = new Object[courses.size()][headers.length];
    for (int i = 0; i < courses.size(); i++) {
      Course course = courses.get(i);
      data[i][0] = course.getId();
      data[i][1] = course.getName();
      data[i][2] = course.getCreditNumber();
    }
    TextTable tt = new TextTable(headers, data);
    tt.printTable();
  }
}
